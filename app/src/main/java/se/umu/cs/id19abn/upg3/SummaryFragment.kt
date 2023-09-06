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

interface OnDataPass {
    fun onDataPass(data: BeerGame)
}

/**
 * A simple ...
 */
class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var beerGameObj: BeerGame
    private var isSaved: Boolean = false
    private lateinit var imageHelper: ImageHelper
    private lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageHelper = ImageHelper()

        beerGameObj = arguments?.let { SummaryFragmentArgs.fromBundle(it).beerGame }!!
        isSaved = arguments?.let { SummaryFragmentArgs.fromBundle(it).isSaved }!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(inflater)

        binding.btnSaveAnalysis.setOnClickListener {
            passData(beerGameObj)
            val action = SummaryFragmentDirections.actionSummaryFragmentToHomeFragment()
            binding.root.findNavController().navigate(action)
        }

        // display data
        binding.sumBeerName.text = beerGameObj.beerName
        // flavours
        binding.bitterNum.text = beerGameObj.flavours.bitter.toString()
        binding.fullnessNum.text = beerGameObj.flavours.fullness.toString()
        binding.sweetnessNum.text = beerGameObj.flavours.sweetness.toString()

        // serve to
        for (i in beerGameObj.servedTo.getChosenItems()) {
            val imageView = ImageView(this.context)
            //setting image resource
            imageView.setImageResource(beerGameObj.servedTo.getIcon(i))
            //adding view to layout
            binding.serveToIconLayout.addView(imageView)
        }

        // description
        binding.sumDescription1.text = beerGameObj.describedAs[0]
        binding.sumDescription2.text = beerGameObj.describedAs[1]
        binding.sumDescription3.text = beerGameObj.describedAs[2]
        binding.sumDescription4.text = beerGameObj.describedAs[3]
        binding.sumDescription5.text = beerGameObj.describedAs[4]

        // display image
        displayImage()

        // hide saved btn if is already saved
        if (isSaved) {
            binding.btnSaveAnalysis.visibility = View.GONE
        }

        // style img view
        if (beerGameObj.imgPath == null) {
            binding.imgViewSum.setBackgroundColor(resources.getColor(R.color.dim_green))
        }

        return binding.root
    }

    private fun passData(data: BeerGame){
        dataPasser.onDataPass(data)
    }

    private fun displayImage() {
        val imgFile = beerGameObj.imgPath?.let { File(it) }
        if (imgFile != null) {
            if (imgFile.exists()) {
                val rotatedBitmap = imageHelper.rotateImage(imgFile.absolutePath)
//                val scaledBitmap = rotatedBitmap?.let { imageHelper.scaleBitmap(it, binding.imgPreview) }
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
