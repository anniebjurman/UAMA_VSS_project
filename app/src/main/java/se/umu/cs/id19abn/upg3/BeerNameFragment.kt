package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentBeerNameBinding
import java.io.File

/**
 * A fragment for displaying input fields for
 * the user to send in information about
 * a beer's name and look.
 */
class BeerNameFragment : Fragment() {
    private lateinit var binding: FragmentBeerNameBinding
    private lateinit var beerGameObj: BeerGame
    private lateinit var imageHelper: ImageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageHelper = ImageHelper()
        // get beerName object from previous fragment
        beerGameObj = arguments?.let { BeerNameFragmentArgs.fromBundle(it).beerGame }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBeerNameBinding.inflate(inflater)

        // if data exists in current beerGame object, show the data in view
        setExistingData()

        binding.btnOpenCamera.setOnClickListener {
            if (binding.beerName.text != null) {
                // save beer name input text if that is entered before taking av img of the beer.
                beerGameObj.beerName = binding.beerName.text.toString()
            }
            // navigate to camera fragment
            val action =
                BeerNameFragmentDirections.actionBeerNameFragmentToCameraFragment(beerGameObj)

            // Return the root view of the inflated layout
            binding.root.findNavController().navigate(action)
        }

        binding.btnNextBeerName.setOnClickListener {
            // save input beer game to BeerGame obj
            beerGameObj.beerName = binding.beerName.text.toString()

            // navigate to next fragment
            val action =
                BeerNameFragmentDirections.actionBeerNameFragmentToFlavorsFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }
        return binding.root
    }

    private fun setExistingData() {
        // Check if the beerName property of beerGameObj is not null
        if (beerGameObj.beerName != null) {
            // Set the text of the beerName EditText to the value of beerName
            binding.beerName.setText(beerGameObj.beerName)
        }
        // Check if the imgPath property of beerGameObj is not null
        if (beerGameObj.imgPath != null) {
            // Call the displayImage() function to display the image
            displayImage()
        }
    }

    private fun displayImage() {
        // Check if the imgPath property of beerGameObj is not null
        val imgFile = beerGameObj.imgPath?.let { File(it) }
        // Check if the imgFile is not null and if the file actually exists
        if (imgFile != null && imgFile.exists()) {
            // Rotate the image if needed using the imageHelper
            val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)

            // Set the rotated bitmap as the image source for the imgPreview ImageView
            binding.imgPreview.setImageBitmap(rotatedBitmap)
        }
    }
}