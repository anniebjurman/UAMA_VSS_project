package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_start_game.number_of_players
import se.umu.cs.id19abn.upg3.databinding.FragmentSignUpBinding
import se.umu.cs.id19abn.upg3.databinding.FragmentStartGameBinding

/**
 * A fragment for knowing when the game is started
 */
class StartGameFragment : Fragment() {
    private lateinit var binding: FragmentStartGameBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        session = arguments?.let { ChooseTypeGameFragmentArgs.fromBundle(it).session }!!
        binding = FragmentStartGameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.gameCode.text = session.gameCode
        binding.gameNameContainer.text = session.gameName

        val db =
            session.gameCode?.let { session.dbHelper?.getDbReference()?.child("games")?.child(it)?.child("members") }

        db?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                val members = dataSnapshot.value as HashMap<*, *>
                var memberString = ""
                var numPlayers = 0

                for ((key, value) in members) {
                    memberString += value.toString() + "\n"
                    numPlayers += 1
                }

                binding.conPlayerText.text = memberString
                val numPlayersString = "($numPlayers st)"
                binding.numberOfPlayers.text = numPlayersString
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("DB", "Failed to read value.", error.toException())
            }
        })

        binding.btnStartGame.setOnClickListener {
            session.currentGame = BeerGame()
            session.gameCode?.let { it1 -> session.dbHelper?.updateGameStatus(it1, GameStatus.STARTED) }

            val action = StartGameFragmentDirections.actionStartGameFragmentToBeerNameFragment(session)
            binding.root.findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}