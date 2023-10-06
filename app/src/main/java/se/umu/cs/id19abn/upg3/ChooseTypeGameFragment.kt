package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentChooseTypeGameBinding

/**
 * A simple [Fragment] subclass.
 */
class ChooseTypeGameFragment : Fragment() {
    private lateinit var binding: FragmentChooseTypeGameBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        session = arguments?.let { ChooseTypeGameFragmentArgs.fromBundle(it).session }!!

        binding = FragmentChooseTypeGameBinding.inflate(layoutInflater)
//        session = arguments?.let { .fromBundle(it).session }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.btnSingle.setOnClickListener {
            session.gameType = GameType.SINGLE
            session.gameName = ""
            session.currentGame = BeerGame()

            val action = ChooseTypeGameFragmentDirections.actionChooseTypeGameFragmentToBeerNameFragment(session)
            binding.root.findNavController().navigate(action)
        }

        binding.btnCreate.setOnClickListener {
            session.gameType = GameType.MULTIPLAYER

            val action = ChooseTypeGameFragmentDirections.actionChooseTypeGameFragmentToCreateGameFragment(session)
            binding.root.findNavController().navigate(action)
        }

        binding.btnJoin.setOnClickListener {
            session.gameType = GameType.MULTIPLAYER

            val action = ChooseTypeGameFragmentDirections.actionChooseTypeGameFragmentToJoinFragment(session)
            binding.root.findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}