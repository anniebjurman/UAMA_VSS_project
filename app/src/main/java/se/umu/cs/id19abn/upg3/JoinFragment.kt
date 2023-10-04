package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val gameCode = binding.joinCode.text.toString()
            session.dbHelper?.addCurrentUserToGame(gameCode.uppercase())
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}