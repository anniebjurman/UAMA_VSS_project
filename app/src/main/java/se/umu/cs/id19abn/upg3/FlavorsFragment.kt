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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.google.android.material.slider.RangeSlider
import se.umu.cs.id19abn.upg3.databinding.FragmentFlavorsBinding

class FlavorsFragment : Fragment() {

    private lateinit var binding: FragmentFlavorsBinding
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
        binding = FragmentFlavorsBinding.inflate(inflater)

        binding.bitterSlider.addOnChangeListener { _, _, _ ->
            beerGameObj.flavours.bitter = binding.bitterSlider.values[0].toInt()
//                Log.d("bitterNum", bitterNum.toString())
        }

        binding.fullnessSlider.addOnChangeListener { _, _, _ ->
            beerGameObj.flavours.fullness = binding.fullnessSlider.values[0].toInt()
//            Log.d("fullnessNum", fullnessNum.toString())
        }

        binding.sweetnessSlider.addOnChangeListener { _, _, _ ->
            beerGameObj.flavours.sweetness = binding.sweetnessSlider.values[0].toInt()
//            Log.d("sweetnessNum", sweetnessNum.toString())
        }

        binding.btnNextFlavors.setOnClickListener {
            Log.d("BUTTON CLICK", "go to serve frag")
            Log.d("BEERNAMEFRAG updated", beerGameObj.toString())

            val action = FlavorsFragmentDirections.actionFlavorsFragmentToServeFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): FlavorsFragment {
            return FlavorsFragment()
        }
    }
}