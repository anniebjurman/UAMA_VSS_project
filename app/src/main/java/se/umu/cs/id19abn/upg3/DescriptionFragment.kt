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
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding
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
        binding = FragmentDescriptionBinding.inflate(inflater)

        binding.btnNextConclusion.setOnClickListener {
            Log.d("BUTTON CLICK", "go to summary")
            beerGameObj.describedAs.addAll(listOf(
                binding.description1.text.toString(),
                binding.description2.text.toString(),
                binding.description3.text.toString(),
                binding.description4.text.toString(),
                binding.description5.text.toString()
            ))

            val action = DescriptionFragmentDirections.actionDescriptionFragmentToSummaryFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): DescriptionFragment {
            return DescriptionFragment()
        }
    }
}