package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentServeBinding


class ServeFragment : Fragment() {

    private lateinit var binding: FragmentServeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServeBinding.inflate(inflater)

        binding.btnNextServe.setOnClickListener {
            Log.d("BUTTON CLICK", "go to description frag")
            val action = ServeFragmentDirections.actionServeFragmentToDescriptionFragment()
            binding.root.findNavController().navigate(action)
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): ServeFragment {
            return ServeFragment()
        }
    }
}