package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentBeerNameBinding
import java.io.File

class BeerNameFragment : Fragment() {
    private lateinit var binding: FragmentBeerNameBinding
    private lateinit var beerGameObj: BeerGame
    private lateinit var imageHelper: ImageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageHelper = ImageHelper()
        beerGameObj = arguments?.let { BeerNameFragmentArgs.fromBundle(it).beerGame }!!
        Log.d("BEERNAMEFRAG from nav", beerGameObj.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBeerNameBinding.inflate(inflater)

        setExistingData()

        binding.btnOpenCamera.setOnClickListener {
            if (binding.beerName.text != null) {
                beerGameObj.beerName = binding.beerName.text.toString()
            }
            val action =
                BeerNameFragmentDirections.actionBeerNameFragmentToCameraFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }

        binding.btnNextBeerName.setOnClickListener {
            // save input beer game to BeerGame obj
            beerGameObj.beerName = binding.beerName.text.toString()

            // navigate to next frag
            val action =
                BeerNameFragmentDirections.actionBeerNameFragmentToFlavorsFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setExistingData() {
        if (beerGameObj.beerName != null) {
            binding.beerName.setText(beerGameObj.beerName)
        }

        if (beerGameObj.imgPath != null) {
            displayImage()
        }
    }

    private fun displayImage() {
        val imgFile = beerGameObj.imgPath?.let { File(it) }
        if (imgFile != null) {
            if (imgFile.exists()) {
                val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)
//                val scaledBitmap = rotatedBitmap?.let { imageHelper.scaleBitmap(it, binding.imgPreview) }
                binding.imgPreview.setImageBitmap(rotatedBitmap)
            }
        }
    }
}