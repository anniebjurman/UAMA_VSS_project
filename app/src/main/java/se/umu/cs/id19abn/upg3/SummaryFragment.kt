package se.umu.cs.id19abn.upg3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentSummaryBinding
import java.io.File

/**
 * A fragment for displaying a summary of the
 * data about a drink, that the user entered.
 */
class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var session: Session
    private var isSaved: Boolean = false
    private lateinit var imageHelper: ImageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        // Called when the fragment is created

        super.onCreate(savedInstanceState)

        // Initialize the imageHelper for image-related operations
        imageHelper = ImageHelper()

        // Retrieve the 'session' object from the arguments bundle passed from the previous fragment
        session = arguments?.let { SummaryFragmentArgs.fromBundle(it).session }!!

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
//            session.currentGame?.let { it1 -> passData(it1) }

            // save data in DB
            session.currentGame?.let { it1 -> session.dbHelper?.addResult("hej", it1) }

            // clear session game
            session.currentGame = null

            // Navigate back to the HomeFragment after saving the analysis
            val action = SummaryFragmentDirections.actionSummaryFragmentToHomeFragment(session)
            binding.root.findNavController().navigate(action)

            // Display a short toast message indicating that the analysis is saved
            Toast.makeText(requireActivity().applicationContext,"Analys sparad", Toast.LENGTH_SHORT).show()
        }

        // Display the beer name
        binding.sumBeerName.text = session.currentGame?.beerName

        // Display flavor data
        binding.bitterNum.text = session.currentGame?.flavours?.bitter.toString()
        binding.fullnessNum.text = session.currentGame?.flavours?.fullness.toString()
        binding.sweetnessNum.text = session.currentGame?.flavours?.sweetness.toString()

        // Display icons for items served to
        for (i in session.currentGame?.servedTo?.getChosenItems()!!) {
            val imageView = ImageView(this.context)
            // Set the image resource based on the chosen item
            imageView.setImageResource(session.currentGame!!.servedTo.getIcon(i))
            // Add the image view to the layout
            binding.serveToIconLayout.addView(imageView)
        }

        // Display description data
        binding.sumDescription1.text = session.currentGame!!.describedAs[0]
        binding.sumDescription2.text = session.currentGame!!.describedAs[1]
        binding.sumDescription3.text = session.currentGame!!.describedAs[2]
        binding.sumDescription4.text = session.currentGame!!.describedAs[3]
        binding.sumDescription5.text = session.currentGame!!.describedAs[4]

        // Display the selected image or set the background color if no image is selected
        displayImage()

        // Hide the "Save Analysis" button if the data is already saved
        if (isSaved) {
            binding.btnSaveAnalysis.visibility = View.GONE
        }

        // Style the image view based on whether an image is available or not
        if (session.currentGame!!.imgPath == null) {
            binding.imgViewSum.setBackgroundColor(resources.getColor(R.color.dim_green))
        }

        return binding.root
    }

    private fun displayImage() {
        // Check if the image file path exists in 'beerGameObj'
        val imgFile = session.currentGame?.imgPath?.let { File(it) }
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
