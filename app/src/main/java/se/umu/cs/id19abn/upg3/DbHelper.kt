package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize

@Parcelize
class DbHelper(var user: String) : Parcelable {
    private var dbReference: DatabaseReference
    private lateinit var userGameNames: ArrayList<String>
    private var userGameObjects: ListBeerGame = ListBeerGame()

    init {
        Log.d("DB HELPER", "init")
        dbReference = getDbReference()
        setAllData(user)
    }

    private fun setAllData(user: String) {
        // get game names
        dbReference.child("users").get().addOnSuccessListener {
            val tmp = it.value as HashMap<*, *>

            for ((key, value ) in tmp) {
                if (key == user) {
                    value as HashMap<*, *>
                    userGameNames = value["games"] as ArrayList<String>
                }
            }

            dbReference.child("games").get().addOnSuccessListener {
                val obj: HashMap<*, *> = it.value as HashMap<*, *>

                for ((key, value ) in obj) {
                    if (userGameNames.contains(key)) {
                        value as HashMap<*, *>
                        val results = value["results"]

                        // get data for a game
                        results as HashMap<*, *>
                        val gameData = results[user]

                        // create a BeerGame object
                        val gameObj = BeerGame()

                        // save data in BeerGame object
                        gameData as HashMap<*, *>
                        gameObj.beerName = gameData["name"] as String
                        gameObj.imgPath = gameData["img_path"] as String

                        val descObj = gameData["described_as"] as ArrayList<*>
                        descObj.forEachIndexed { index, element ->
                            gameObj.describedAs[index] = element as String
                        }

                        val flavours = gameData["flavours"]
                        flavours as HashMap<*, *>
                        gameObj.flavours.bitter = (flavours["bitter"] as Long).toInt()
                        gameObj.flavours.fullness = (flavours["fullness"] as Long).toInt()
                        gameObj.flavours.sweetness = (flavours["sweetness"] as Long).toInt()

                        val servedTo = gameData["served_to"] as ArrayList<*>
                        for (icon in servedTo) {
                            Log.d("ICON", icon.toString())
                            gameObj.servedTo.toggleChosenIcon(icon.toString())
                        }

                        // save BeerGame in ListBeerGame
                        userGameObjects.beerGames.add(gameObj)
                        Log.d("OBJ ADD", userGameObjects.beerGames.toString())
                    }
                }
            }
        }
    }

    private fun getDbReference(): DatabaseReference {
        val db = Firebase.database("https://vad-sager-systemet-default-rtdb.europe-west1.firebasedatabase.app/")
        return db.reference
    }

    fun getUserGameNames() : ArrayList<String> {
        return userGameNames
    }

    fun getUserGameObjects(): ListBeerGame {
        return userGameObjects
    }

    fun getUserGameNames(user: String): ArrayList<String>? {
        var gameNames: ArrayList<String>? = null

        dbReference.child("users").get().addOnSuccessListener {
            val tmp = it.value as HashMap<*, *>

            for ((key, value ) in tmp) {
                if (key == user) {
                    value as HashMap<*, *>
                    gameNames = value["games"] as ArrayList<String>
                }
            }
        }
        return gameNames
    }

    fun getUserGameObjects(user: String): ListBeerGame {
        val gameNames = getUserGameNames(user)
        Log.d("GAME NAMES", gameNames.toString())
        val gameObjects = ListBeerGame()

        dbReference.child("games").get().addOnSuccessListener {
            val obj: HashMap<*, *> = it.value as HashMap<*, *>

            for ((key, value ) in obj) {

                if (gameNames?.contains(key) == true) {
                    value as HashMap<*, *>
                    val results = value["results"]

                    results as HashMap<*, *>
                    val gameObj = results[user]
                }
            }
        }
        return gameObjects
    }

}