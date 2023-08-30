package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import se.umu.cs.id19abn.upg3.databinding.FragmentSummaryBinding

/**
 * A simple ...
 */
class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var beerGameObj: BeerGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beerGameObj = arguments?.let { BeerNameFragmentArgs.fromBundle(it).beerGame }!!
        Log.d("BEERNAMEFRAG from nav", beerGameObj.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): SummaryFragment {
            return SummaryFragment()
        }
    }
}