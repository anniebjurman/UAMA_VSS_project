package se.umu.cs.id19abn.upg3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentSummaryBinding
import java.io.File

/**
 * An interface to use to send data from
 * an fragment to and activity.
 */
interface OnDataPass {
    fun onDataPass(data: BeerGame)
}

/**
 * A fragment for displaying a summary of the
 * data about a drink, that the user entered.
 */
class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var beerGameObj: BeerGame
    private var isSaved: Boolean = false
    private lateinit var imageHelper: ImageHelper
    private lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        // Called when the fragment is attached to the activity
        super.onAttach(context)
        // Ensure that the hosting activity implements the OnDataPass interface
        // and assign it to the dataPasser variable for communication
        dataPasser = context as OnDataPass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Called when the fragment is created

        super.onCreate(savedInstanceState)

        // Initialize the imageHelper for image-related operations
        imageHelper = ImageHelper()

        // Retrieve the 'beerGameObj' object from the arguments bundle passed from the previous fragment
        beerGameObj = arguments?.let { SummaryFragmentArgs.fromBundle(it).beerGame }!!

        // Retrieve the 'isSaved' boolean flag from the arguments bundle
        // to determine whether the data is saved or not
        isSaved = arguments?.let { SummaryFragmentArgs.fromBundle(it).isSaved }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using the FragmentSummaryBinding
        binding = FragmentSummaryBinding.inflate(inflater)

        // Define an OnClickListener for the "Save Analysis" button
        binding.btnSaveAnalysis.setOnClickListener {
            // Pass the 'beerGameObj' data to the hosting activity using the 'passData' function
            passData(beerGameObj)

            // Navigate back to the HomeFragment after saving the analysis
            val action = SummaryFragmentDirections.actionSummaryFragmentToHomeFragment()
            binding.root.findNavController().navigate(action)
        }

        // Display the beer name
        binding.sumBeerName.text = beerGameObj.beerName

        // Display flavor data
        binding.bitterNum.text = beerGameObj.flavours.bitter.toString()
        binding.fullnessNum.text = beerGameObj.flavours.fullness.toString()
        binding.sweetnessNum.text = beerGameObj.flavours.sweetness.toString()

        // Display icons for items served to
        for (i in beerGameObj.servedTo.getChosenItems()) {
            val imageView = ImageView(this.context)
            // Set the image resource based on the chosen item
            imageView.setImageResource(beerGameObj.servedTo.getIcon(i))
            // Add the image view to the layout
            binding.serveToIconLayout.addView(imageView)
        }

        // Display description data
        binding.sumDescription1.text = beerGameObj.describedAs[0]
        binding.sumDescription2.text = beerGameObj.describedAs[1]
        binding.sumDescription3.text = beerGameObj.describedAs[2]
        binding.sumDescription4.text = beerGameObj.describedAs[3]
        binding.sumDescription5.text = beerGameObj.describedAs[4]

        // Display the selected image or set the background color if no image is selected
        displayImage()

        // Hide the "Save Analysis" button if the data is already saved
        if (isSaved) {
            binding.btnSaveAnalysis.visibility = View.GONE
        }

        // Style the image view based on whether an image is available or not
        if (beerGameObj.imgPath == null) {
            binding.imgViewSum.setBackgroundColor(resources.getColor(R.color.dim_green))
        }

        return binding.root
    }

    // Function to pass data to the hosting activity
    private fun passData(data: BeerGame){
        dataPasser.onDataPass(data)
    }

    private fun displayImage() {
        // Check if the image file path exists in 'beerGameObj'
        val imgFile = beerGameObj.imgPath?.let { File(it) }
        if (imgFile != null) {
            // Check if the image file itself exists on the device
            if (imgFile.exists()) {
                // Rotate the image (if needed) and set it in the 'binding.imgViewSum' ImageView
                val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)
                binding.imgViewSum.setImageBitmap(rotatedBitmap)
            }
        }
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
