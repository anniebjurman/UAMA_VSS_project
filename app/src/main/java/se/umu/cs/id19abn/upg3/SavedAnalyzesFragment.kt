package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
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
            // this method inflates the single item layout
            // inside the parent linear layout
            val inf = LayoutInflater.from(activity).inflate(R.layout.beer_game_item, container, false)

            val beerNameView = inf.findViewById<TextView>(R.id.item_beer_name)
            beerNameView.text = bg.beerName

            val beerImgView = inf.findViewById<ImageView>(R.id.item_beer_img)
            bg.imgPath?.let { displayImage(it, beerImgView) }
            binding.parentLinearLayout.addView(inf, binding.parentLinearLayout.childCount)
        }

        return binding.root
    }

    private fun displayImage(imgPath: String, view: ImageView) {
        val imgFile = File(imgPath)
        if (imgFile.exists()) {
            val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)
//                val scaledBitmap = rotatedBitmap?.let { imageHelper.scaleBitmap(it, binding.imgPreview) }
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