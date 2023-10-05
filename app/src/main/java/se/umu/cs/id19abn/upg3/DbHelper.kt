package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize

class BeerGameDB(bg: BeerGame) {
    var described_as: ArrayList<String> = ArrayList(bg.describedAs.values)
    var flavours: Flavours = bg.flavours
    var img_path: String = bg.imgPath.toString()
    var name: String = bg.beerName.toString()
    var served_to: ArrayList<String> = bg.servedTo.getChosenItems()
    var type: String = "beer"
}

class User(userName: String, mail: String) {
    var name: String = userName
    var email: String = mail
}

@Parcelize
class DbHelper(val user: String? = null) : Parcelable {
    private var dbReference: DatabaseReference
    private var userGameObjects: ListBeerGame = ListBeerGame()
    private lateinit var userName: String

    init {
        Log.d("DB HELPER", "init")
        dbReference = getDbReference()

        if (user != null) {
            userName = user
            setGamesListener()
        }
    }

    private fun setGamesListener() {
        val dbPath = dbReference.child("games")

        dbPath.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                Log.d("SET GAME LISTENER", "change in games (db)")
                userGameObjects.beerGames.clear()

                for (game in dataSnapshot.children) {
                    val value = game.value as HashMap<*, *>
                    val results = value["results"]

                    if (results != null) {
                        results as HashMap<*, *>
                        for ((user, bgData) in results) {
                            if (user.toString() == userName) {
                                // create a BeerGame object
                                val gameObj = BeerGame()

                                // save data in BeerGame object
                                bgData as HashMap<*, *>
                                Log.d("BG DATA", bgData.toString())
                                gameObj.beerName = bgData["name"] as String
                                gameObj.imgPath = bgData["img_path"] as String

                                val descObj = bgData["described_as"] as ArrayList<*>
                                descObj.forEachIndexed { index, element ->
                                    gameObj.describedAs[index] = element as String
                                }

                                val flavours = bgData["flavours"]
                                flavours as HashMap<*, *>
                                gameObj.flavours.bitter = (flavours["bitter"] as Long).toInt()
                                gameObj.flavours.fullness = (flavours["fullness"] as Long).toInt()
                                gameObj.flavours.sweetness = (flavours["sweetness"] as Long).toInt()

                                val servedTo = bgData["served_to"] as ArrayList<*>
                                for (icon in servedTo) {
                                    gameObj.servedTo.toggleChosenIcon(icon.toString())
                                }

                                // save BeerGame in ListBeerGame
                                userGameObjects.beerGames.add(gameObj)
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("DB", "Failed to read value.", error.toException())
            }
        })
    }

    fun addUser(userName: String, email: String) {
        dbReference.child("users").child(userName).setValue(User("NAME", email))
    }

    fun addGame(gameName: String, gameCode: String) {
        dbReference.child("games").child(gameCode).child("name").setValue(gameName)
    }

    fun addCurrentUserToGame(gameCode: String) {
        dbReference.child("games").child(gameCode).child("members").push().setValue(userName)
        dbReference.child("users").child(userName).child("games").push().setValue(gameCode)
    }

    fun getRandomGameCode(): String {
        val allowedChars = ('A'..'N') + ('P'..'Z') + ('1'..'9')
        return (1..8)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun addResult(gameName: String, bg: BeerGame) {
        dbReference.child("games").child(gameName).child("results")
            .child(userName).setValue(BeerGameDB(bg))
    }

    fun getDbReference(): DatabaseReference {
        val db = Firebase.database("https://vad-sager-systemet-default-rtdb.europe-west1.firebasedatabase.app/")
        return db.reference
    }

    fun getUserGameObjects(): ListBeerGame {
        return userGameObjects
    }

}