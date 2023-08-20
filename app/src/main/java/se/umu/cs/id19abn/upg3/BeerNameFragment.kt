package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels

class BeerNameFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var flavorsFragment: Fragment
//    private var beerGameViewModel = BeerGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_beer_name, container, false)

        nextButton = view.findViewById(R.id.btn_next_step)
        flavorsFragment = FlavorsFragment()

        nextButton.setOnClickListener {
            // save beer name in view model


            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_view, flavorsFragment)
            transaction.commit()
        }

        return view
    }
}