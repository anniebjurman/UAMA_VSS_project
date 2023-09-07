package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentFlavorsBinding

/**
 * A fragment for displaying input fields for
 * the user to send in information about
 * a beer's different flavors.
 */
class FlavorsFragment : Fragment() {

    private lateinit var binding: FragmentFlavorsBinding
    private lateinit var beerGameObj: BeerGame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get beerName object from previous fragment
        beerGameObj = arguments?.let { FlavorsFragmentArgs.fromBundle(it).beerGame }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using the provided inflater
        binding = FragmentFlavorsBinding.inflate(inflater)

        // Set existing data for the sliders
        setExistingData()

        // Add a change listener to the bitter slider to update 'beerGameObj' when the value changes
        binding.bitterSlider.addOnChangeListener { _, _, _ ->
            beerGameObj.flavours.bitter = binding.bitterSlider.values[0].toInt()
        }

        // Add a change listener to the fullness slider to update 'beerGameObj' when the value changes
        binding.fullnessSlider.addOnChangeListener { _, _, _ ->
            beerGameObj.flavours.fullness = binding.fullnessSlider.values[0].toInt()
        }

        // Add a change listener to the sweetness slider to update 'beerGameObj' when the value changes
        binding.sweetnessSlider.addOnChangeListener { _, _, _ ->
            beerGameObj.flavours.sweetness = binding.sweetnessSlider.values[0].toInt()
        }

        // Set a click listener for the "Next" button to navigate to the ServeFragment
        binding.btnNextFlavors.setOnClickListener {
            val action = FlavorsFragmentDirections.actionFlavorsFragmentToServeFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }

        // Return the root view of the inflated layout
        return binding.root
    }

    private fun setExistingData() {
        // Set the initial slider values based on existing data from 'beerGameObj'
        binding.bitterSlider.setValues(beerGameObj.flavours.bitter.toFloat())
        binding.fullnessSlider.setValues(beerGameObj.flavours.fullness.toFloat())
        binding.sweetnessSlider.setValues(beerGameObj.flavours.sweetness.toFloat())
    }

    companion object {
        fun newInstance(): FlavorsFragment {
            return FlavorsFragment()
        }
    }
}