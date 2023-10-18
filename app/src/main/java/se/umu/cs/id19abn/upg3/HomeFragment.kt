package se.umu.cs.id19abn.upg3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import se.umu.cs.id19abn.upg3.databinding.FragmentHomeBinding

/**
 * A fragment for displaying the apps start view.
 * Where the user can read about how to use the app,
 * start a game, and navigate to saved analyses.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get session obj from previous fragment
        session = arguments?.let { HomeFragmentArgs.fromBundle(it).session }!!
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using the provided inflater
        binding = FragmentHomeBinding.inflate(inflater)

        // Set a click listener for the "Beer" button to navigate to the BeerNameFragment
        binding.btnBeer.setOnClickListener {
            // Create a new BeerGame object
//            session.currentGame = BeerGame()

            // Create a navigation action to go to the BeerNameFragment with the new BeerGame object
//            val action = HomeFragmentDirections.actionHomeFragmentToBeerNameFragment(session)
            val action = HomeFragmentDirections.actionHomeFragmentToChooseTypeGameFragment(session)

            // Navigate to the BeerNameFragment
            binding.root.findNavController().navigate(action)
        }

        // Set a click listener for the "Info" button to display a popup window
        binding.btnInfo.setOnClickListener {
            // Inflate the game information layout for the popup
            val popupView = LayoutInflater.from(activity).inflate(R.layout.game_information, container, false)

            // Define popup window parameters
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true // Allows taps outside the popup to dismiss it

            // Create a popup window with the specified parameters
            val popupWindow = PopupWindow(popupView, width, height, focusable)

            // Show the popup window at the center of the screen
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

            // Dismiss the popup window when it is touched
            popupView.setOnTouchListener { _, _ ->
                popupWindow.dismiss()
                true
            }
        }

        binding.btnLogOut.setOnClickListener {
            // Create a navigation action to go to the SignInFragment
            val action = HomeFragmentDirections.actionHomeFragmentToSignInFragment()

            // Navigate to the SignInFragment
            binding.root.findNavController().navigate(action)
        }

        // Return the root view of the inflated layout
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment
         */
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}