package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import se.umu.cs.id19abn.upg3.databinding.FragmentBeerNameBinding
import java.io.File

/**
 * A fragment for displaying input fields for
 * the user to send in information about
 * a beer's name and look.
 */
class BeerNameFragment : Fragment() {
    private lateinit var binding: FragmentBeerNameBinding
    private lateinit var session: Session
    private lateinit var imageHelper: ImageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageHelper = ImageHelper()
        // get session obj from previous fragment
        session = arguments?.let { BeerNameFragmentArgs.fromBundle(it).session }!!
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
                session.currentGame?.beerName = binding.beerName.text.toString()
            }
            // navigate to camera fragment
            val action =
                BeerNameFragmentDirections.actionBeerNameFragmentToCameraFragment(session)

            // Return the root view of the inflated layout
            binding.root.findNavController().navigate(action)
        }

        binding.btnNextBeerName.setOnClickListener {
            // save input beer game to BeerGame obj
            session.currentGame?.beerName = binding.beerName.text.toString()

            // navigate to next fragment
            val action =
                BeerNameFragmentDirections.actionBeerNameFragmentToFlavorsFragment(session)
            binding.root.findNavController().navigate(action)
        }
        return binding.root
    }

    private fun setExistingData() {
        // Check if the beerName property of beerGameObj is not null
        if (session.currentGame?.beerName != null) {
            // Set the text of the beerName EditText to the value of beerName
            binding.beerName.setText(session.currentGame!!.beerName)
        }
        // Check if the imgPath property of beerGameObj is not null
        if (session.currentGame?.imgPath != null) {
            // Call the displayImage() function to display the image
            displayImage()
        }
    }

    private fun displayImage() {
        // Check if the imgPath property of beerGameObj is not null
        val imgFile = session.currentGame?.imgPath?.let { File(it) }
        // Check if the imgFile is not null and if the file actually exists
        if (imgFile != null && imgFile.exists()) {
            // Rotate the image if needed using the imageHelper
            val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)

            // Set the rotated bitmap as the image source for the imgPreview ImageView
            binding.imgPreview.setImageBitmap(rotatedBitmap)
        }
    }
}