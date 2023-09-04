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
import se.umu.cs.id19abn.upg3.databinding.FragmentSavedAnalyzesBinding
import java.io.File

/**
 * A simple
 */
class SavedAnalyzesFragment : Fragment() {
    private lateinit var binding: FragmentSavedAnalyzesBinding
    private lateinit var listBeerGame: ListBeerGame
    private lateinit var imageHelper: ImageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageHelper = ImageHelper()

        listBeerGame = arguments?.let { SavedAnalyzesFragmentArgs.fromBundle(it).listBeerGame }!!
        Log.d("LISTBEERGAME from nav", listBeerGame.beerGames.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSavedAnalyzesBinding.inflate(inflater)

        listBeerGame.beerGames.forEach { bg ->
            Log.d("LOOP", "in loop")
            // inflate beer_game_item inside the parent linear layout
            val view = LayoutInflater.from(activity).inflate(R.layout.beer_game_item, container, false)

            val beerNameView = view.findViewById<TextView>(R.id.item_beer_name)
            beerNameView.text = bg.beerName

            val beerImgView = view.findViewById<ImageView>(R.id.item_beer_img)

            if (bg.imgPath == null) {
                beerImgView.setBackgroundColor(resources.getColor(R.color.dim_green))
            } else {
                displayImage(bg.imgPath!!, beerImgView)
            }

            binding.parentLinearLayout.addView(view, binding.parentLinearLayout.childCount)

            // setup clickListeners
            view.setOnClickListener {
                Log.d("CLICK", "on analysed")
                val action = SavedAnalyzesFragmentDirections.actionSavedAnalyzesFragmentToSummaryFragment(bg, true)
                binding.root.findNavController().navigate(action)
            }
        }

        return binding.root
    }

    private fun displayImage(imgPath: String, view: ImageView) {
        val imgFile = File(imgPath)
        if (imgFile.exists()) {
            val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)
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