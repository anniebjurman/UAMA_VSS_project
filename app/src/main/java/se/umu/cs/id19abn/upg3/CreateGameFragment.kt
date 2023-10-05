package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentCreateGameBinding

/**
 * A simple [Fragment] subclass.
 */
class CreateGameFragment : Fragment() {
    private lateinit var binding: FragmentCreateGameBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        session = arguments?.let { ChooseTypeGameFragmentArgs.fromBundle(it).session }!!
        binding = FragmentCreateGameBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnCreateGame.setOnClickListener {
            val gameName = binding.gameName.text.toString()

            if (gameName.length >= 3) {
                session.gameName = gameName
                val gameCode = session.dbHelper?.getRandomGameCode()
                if (gameCode != null) {
                    session.dbHelper?.addGame(gameName, gameCode)
                    session.dbHelper?.addCurrentUserToGame(gameCode)
                    session.dbHelper?.updateGameStatus(gameCode, GameStatus.PENDING)
                    session.gameCode = gameCode

                    val action = CreateGameFragmentDirections.actionCreateGameFragmentToStartGameFragment(session)
                    binding.root.findNavController().navigate(action)
                } else {
                    Log.e("CreateGameFragment", "dbHelper does not exist in session")
                }
            } else {
                Toast.makeText(requireActivity().applicationContext,"Namnet måste innehålla minst 3 tecken", Toast.LENGTH_SHORT).show()
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}