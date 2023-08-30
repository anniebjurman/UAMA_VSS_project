package se.umu.cs.id19abn.upg3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentBeerNameBinding

class BeerNameFragment : Fragment() {
    private lateinit var binding: FragmentBeerNameBinding
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
        binding = FragmentBeerNameBinding.inflate(inflater)

        binding.btnOpenCamera.setOnClickListener {
            Log.d("BUTTON CLICK", "open camera")
            val action = BeerNameFragmentDirections.actionBeerNameFragmentToCameraActivity()
            binding.root.findNavController().navigate(action)
        }

        binding.btnNextBeerName.setOnClickListener {
            Log.d("BUTTON CLICK", "go to flavour frag")

            // save input beer game to BeerGame obj
            beerGameObj.beerName = binding.beerName.text.toString()
            Log.d("BEERNAMEFRAG updated", beerGameObj.toString())

            // navigate to next frag
            val action = BeerNameFragmentDirections.actionBeerNameFragmentToFlavorsFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }

        return binding.root
    }
}