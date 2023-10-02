package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import se.umu.cs.id19abn.upg3.databinding.FragmentSavedAnalyzesBinding
import java.io.File
import kotlin.coroutines.CoroutineContext

/**
 * A fragment for displaying a list
 * of saved analyses of drinks.
 */
class SavedAnalyzesFragment : Fragment() { //CoroutineScope
    private lateinit var binding: FragmentSavedAnalyzesBinding
    private lateinit var session: Session
    private lateinit var imageHelper: ImageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize an ImageHelper instance for image-related operations
        imageHelper = ImageHelper()
        // Get the 'listBeerGame' data passed from the previous fragment
        session = arguments?.let { SavedAnalyzesFragmentArgs.fromBundle(it).session }!!
//        session.user?.let { gameNames = dbHelper.getUserGameNames(it)!! }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedAnalyzesBinding.inflate(inflater)

        Log.d("GAMEOBJ IN SAVEDFRAG", session.dbHelper?.getUserGameObjects()?.beerGames.toString())

        //Iterate through the 'beerGames' list in 'listBeerGame'
        session.dbHelper?.getUserGameObjects()?.beerGames?.forEach { bg ->
            // Inflate the 'beer_game_item' layout inside the parent linear layout
            val view = LayoutInflater.from(activity).inflate(R.layout.beer_game_item, container, false)

            // Find the TextView for displaying beer names and set its text
            val beerNameView = view.findViewById<TextView>(R.id.item_beer_name)
            beerNameView.text = bg.beerName

            // Find the ImageView for displaying beer images
            val beerImgView = view.findViewById<ImageView>(R.id.item_beer_img)

            // Check if the image path is null
            if (bg.imgPath == null) {
                // Set the background color to dim green if there is no image
                beerImgView.setBackgroundColor(resources.getColor(R.color.dim_green))
            } else {
                // Display the image using the 'displayImage' function
                displayImage(bg.imgPath!!, beerImgView)
            }

            // Add the inflated view to the parent linear layout
            binding.parentLinearLayout.addView(view, binding.parentLinearLayout.childCount)

            // Setup click listeners for each beer item
            view.setOnClickListener {
                // Create a navigation action to go to the SummaryFragment with the selected beer data
                session.currentGame = bg
                val action = SavedAnalyzesFragmentDirections.actionSavedAnalyzesFragmentToSummaryFragment(true, session)

                // Navigate to the SummaryFragment
                binding.root.findNavController().navigate(action)
            }
        }
        return binding.root
    }

    private fun displayImage(imgPath: String, view: ImageView) {
        // Create a File object using the provided image path
        val imgFile = File(imgPath)

        // Check if the image file exists on the device
        if (imgFile.exists()) {
            // Rotate the image if needed using the 'rotateImage' function from 'imageHelper'
            val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)

            // Set the rotated bitmap as the image source for the provided ImageView
            view.setImageBitmap(rotatedBitmap)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         */
        fun newInstance(): ServeFragment {
            return ServeFragment()
        }
    }
}