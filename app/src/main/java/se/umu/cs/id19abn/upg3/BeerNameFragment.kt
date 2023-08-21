package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels

class BeerNameFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var flavorsFragment: Fragment

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
            Log.d("BUTTON CLICK", "click")

            val beerName = "A Ship Full Of IPA"
            setFragmentResult("requestBeerNameKey", bundleOf("bundleBeerNameKey" to beerName))

            val bitter = 2
            setFragmentResult("requestBitterKey", bundleOf("bundleBitterKey" to bitter))

            // display flavor fragment
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_view, flavorsFragment)
            transaction.commit()
        }

        return view
    }
}