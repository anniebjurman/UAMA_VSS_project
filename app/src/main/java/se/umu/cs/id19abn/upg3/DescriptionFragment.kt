package se.umu.cs.id19abn.upg3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentDescriptionBinding

/**
 * A fragment for displaying input fields for
 * the user to send in information about
 * how a beer is described.
 */
class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var beerGameObj: BeerGame
    private var textFieldViews: ArrayList<EditText> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get beerName object from previous fragment
        beerGameObj = arguments?.let { DescriptionFragmentArgs.fromBundle(it).beerGame }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using the provided inflater
        binding = FragmentDescriptionBinding.inflate(inflater)

        // Add the text field views to the textFieldViews list for easy access
        if (textFieldViews.isEmpty()) {
            textFieldViews.addAll(
                listOf(
                    binding.description1,
                    binding.description2,
                    binding.description3,
                    binding.description4,
                    binding.description5
                )
            )
        }

        // Set existing data in the text field views
        setExistingData()

        // Add a text changed listener to each text field view to update the beerGameObj
        textFieldViews.forEachIndexed{ index, entry ->
            entry.addTextChangedListener {
                beerGameObj.describedAs[index] = entry.text.toString()
            }
        }
        // Set a click listener for the "Next" button to navigate to the SummaryFragment
        binding.btnNextConclusion.setOnClickListener {
            val action =
                DescriptionFragmentDirections.actionDescriptionFragmentToSummaryFragment(beerGameObj, false)
            binding.root.findNavController().navigate(action)
        }
        // Return the root view of the inflated layout
        return binding.root
    }

    private fun setExistingData() {
        // Iterate through the 'describedAs' entries in 'beerGameObj'
        beerGameObj.describedAs.forEach { entry ->
            // Use the key (index) to access the corresponding text field view in 'textFieldViews'
            // and set its text to the value from the 'describedAs' entry
            textFieldViews[entry.key].setText(entry.value)
        }
    }

    companion object {
        fun newInstance(): DescriptionFragment {
            return DescriptionFragment()
        }
    }
}