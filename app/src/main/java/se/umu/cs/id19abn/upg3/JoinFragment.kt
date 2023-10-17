package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.text.toUpperCase
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import se.umu.cs.id19abn.upg3.databinding.FragmentJoinBinding

/**
 * A simple [Fragment] subclass.
 */
class JoinFragment : Fragment() {
    private lateinit var binding: FragmentJoinBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentJoinBinding.inflate(layoutInflater)
        session = arguments?.let { ChooseTypeGameFragmentArgs.fromBundle(it).session }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnJoin.setOnClickListener {
            val gameCode = binding.joinCode.text.toString().uppercase()

            if (gameCode != "") {
                val db = session.dbHelper?.getDbReference()?.child("games")?.get()

                db?.addOnSuccessListener {
                        val games = it.value as HashMap<*, *>

                        if (games.keys.contains(gameCode)) {
                            val res = games[gameCode] as HashMap<*, *>
                            val status = res["status"]

                            if (status == GameStatus.PENDING.toString()) {
                                session.currentGame = BeerGame()
                                session.gameCode = gameCode
                                session.dbHelper?.addCurrentUserToGame(gameCode)
                                navigateToWaitJoinFragment()

                            } else {
                                Toast.makeText(
                                    requireActivity().applicationContext,
                                    "Felaktig spelkod (status)",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                requireActivity().applicationContext,
                                "Felaktig spelkod (no game)",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            } else {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Ange en spelkod",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun navigateToWaitJoinFragment() {
        val action =
            JoinFragmentDirections.actionJoinFragmentToWaitJoinFragment(
                session
            )
        binding.root.findNavController().navigate(action)
    }
}