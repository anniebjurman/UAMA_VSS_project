package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var beerGameObj: BeerGame
    private var textFieldViews: ArrayList<EditText> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beerGameObj = arguments?.let { BeerNameFragmentArgs.fromBundle(it).beerGame }!!
        Log.d("BEERNAMEFRAG from nav", beerGameObj.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater)
        textFieldViews.addAll(
            listOf(
                binding.description1,
                binding.description2,
                binding.description3,
                binding.description4,
                binding.description5
            )
        )

        setExistingData()

        textFieldViews.forEachIndexed{ index, entry ->
            entry.addTextChangedListener {
                beerGameObj.describedAs[index] = entry.text.toString()
            }
        }

        binding.btnNextConclusion.setOnClickListener {
            val action =
                DescriptionFragmentDirections.actionDescriptionFragmentToSummaryFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setExistingData() {
        beerGameObj.describedAs.forEach { entry ->
            textFieldViews[entry.key].setText(entry.value)
        }
    }

    companion object {
        fun newInstance(): DescriptionFragment {
            return DescriptionFragment()
        }
    }
}