package se.umu.cs.id19abn.upg3

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
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

        // display data
        binding.sumBeerName.text = beerGameObj.beerName
        // flavours
        binding.bitterNum.text = beerGameObj.flavours.bitter.toString()
        binding.fullnessNum.text = beerGameObj.flavours.fullness.toString()
        binding.sweetnessNum.text = beerGameObj.flavours.sweetness.toString()

        // serve to
        for (i in beerGameObj.servedTo.getChosenItems()) {
            val imageView = ImageView(this.context)
            //setting image resource
            imageView.setImageResource(beerGameObj.servedTo.getIcon(i))
            //adding view to layout
            binding.serveToIconLayout.addView(imageView)
        }

        // description
        binding.sumDescription1.text = beerGameObj.describedAs[0]
        binding.sumDescription2.text = beerGameObj.describedAs[1]
        binding.sumDescription3.text = beerGameObj.describedAs[2]
        binding.sumDescription4.text = beerGameObj.describedAs[3]
        binding.sumDescription5.text = beerGameObj.describedAs[4]

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