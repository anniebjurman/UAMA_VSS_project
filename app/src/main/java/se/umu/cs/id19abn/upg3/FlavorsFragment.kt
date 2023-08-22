package se.umu.cs.id19abn.upg3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.google.android.material.slider.RangeSlider

class FlavorsFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var serveFragment: Fragment
    private lateinit var bitterSlider: RangeSlider
    private lateinit var fullnessSlider: RangeSlider
    private lateinit var sweetnessSlider: RangeSlider

    private var bitterNum: Int = 0
    private var fullnessNum: Int = 0
    private var sweetnessNum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback is only called when MyFragment is at least started
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_flavors, container, false)

        nextButton = view.findViewById(R.id.btn_next_flavors)
        serveFragment = ServeFragment()
        bitterSlider = view.findViewById(R.id.bitter_slider)
        fullnessSlider = view.findViewById(R.id.fullness_slider)
        sweetnessSlider = view.findViewById(R.id.sweetness_slider)

        bitterSlider.addOnChangeListener { slider, value, fromUser ->
                bitterNum = bitterSlider.values[0].toInt()
                Log.d("bitterNum", bitterNum.toString())
            }

        fullnessSlider.addOnChangeListener { slider, value, fromUser ->
            fullnessNum = fullnessSlider.values[0].toInt()
            Log.d("fullnessNum", fullnessNum.toString())
        }

        sweetnessSlider.addOnChangeListener { slider, value, fromUser ->
            sweetnessNum = sweetnessSlider.values[0].toInt()
            Log.d("sweetnessNum", sweetnessNum.toString())
        }

        nextButton.setOnClickListener {
            val sliderValues = intArrayOf(bitterNum, fullnessNum, sweetnessNum)
            setFragmentResult("requestFlavourKey", bundleOf("bundleFlavourKey" to sliderValues))

            // display beerNameFragment
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container_view, serveFragment)
            transaction.commit()
        }
        return view
    }
}