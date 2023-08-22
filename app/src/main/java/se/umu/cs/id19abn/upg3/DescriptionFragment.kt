package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult

class DescriptionFragment : Fragment() {

    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback is only called when MyFragment is at least started
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_description, container, false)

        nextButton = view.findViewById(R.id.btn_next_beer_name)
//        flavorsFragment = FlavorsFragment()

//        nextButton.setOnClickListener {
//            // display flavor fragment
//            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
//            transaction.replace(R.id.fragment_container_view, flavorsFragment)
//            transaction.commit()
//        }

        return view
    }
}