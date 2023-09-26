package se.umu.cs.id19abn.upg3

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.findNavController
import se.umu.cs.id19abn.upg3.databinding.FragmentServeBinding

/**
 * A fragment for displaying input fields for
 * the user to send in information about
 * what the beer is best served to.
 */
class ServeFragment : Fragment() {

    private lateinit var binding: FragmentServeBinding
    private lateinit var session: Session
    private var serveButtons: ArrayList<ImageButton> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the 'beerGameObj' from the previous fragment's arguments
        session = arguments?.let { ServeFragmentArgs.fromBundle(it).session }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentServeBinding.inflate(inflater)

        // Create a list of serve buttons and add them to 'serveButtons'
        serveButtons.addAll(
            listOf(
                binding.btnBbq,
                binding.btnBird,
                binding.btnCheese,
                binding.btnCow,
                binding.btnChili,
                binding.btnDessert,
                binding.btnFish,
                binding.btnGlass,
                binding.btnRadish,
                binding.btnTemple,
                binding.btnPig,
                binding.btnMoose,
                binding.btnShrimp,
                binding.btnSheep
            )
        )

        // Set existing data on the serve buttons
        setExistingData()

        // Setup click listeners for each serve button
        for (s in serveButtons.indices) {
            // Click -> Mark that dice
            serveButtons[s].setOnClickListener {
                toggleServeButton(s)
                updateServeImageButton(s)
            }
        }

        // Set a click listener for the "Next" button to navigate to the DescriptionFragment
        binding.btnNextServe.setOnClickListener {
            val action =
                ServeFragmentDirections.actionServeFragmentToDescriptionFragment(session)
            binding.root.findNavController().navigate(action)
        }

        // Return the root view of the inflated layout
        return binding.root
    }

    private fun setExistingData() {
        val chosenItems: ArrayList<String>? = session.currentGame?.servedTo?.getChosenItems()

        // Create a map to map item names to their corresponding button indices
        val itemToButtonIndex = mapOf(
            "bbq" to 0, "bird" to 1, "cheese" to 2, "cow" to 3, "chili" to 4,
            "dessert" to 5, "fish" to 6, "glass" to 7, "radish" to 8,
            "temple" to 9, "pig" to 10, "moose" to 11, "shrimp" to 12, "sheep" to 13
        )

        chosenItems?.forEach { item ->
            // Check if the item is mapped to a button index and call 'updateServeImageButton'
            itemToButtonIndex[item]?.let { buttonIndex ->
                updateServeImageButton(buttonIndex)
            }
        }
    }

    private fun toggleServeButton(btnIndex: Int) {
        // Toggle properties in 'servedTo' of 'beerGameObj'
        when (btnIndex) {
            0 -> session.currentGame?.servedTo?.bbq = !session.currentGame?.servedTo?.bbq!!
            1 -> session.currentGame?.servedTo?.bird = !session.currentGame?.servedTo?.bird!!
            2 -> session.currentGame?.servedTo?.cheese = !session.currentGame?.servedTo?.cheese!!
            3 -> session.currentGame?.servedTo?.cow = !session.currentGame?.servedTo?.cow!!
            4 -> session.currentGame?.servedTo?.chili = !session.currentGame?.servedTo?.chili!!
            5 -> session.currentGame?.servedTo?.dessert = !session.currentGame?.servedTo?.dessert!!
            6 -> session.currentGame?.servedTo?.fish = !session.currentGame?.servedTo?.fish!!
            7 -> session.currentGame?.servedTo?.glass = !session.currentGame?.servedTo?.glass!!
            8 -> session.currentGame?.servedTo?.radish = !session.currentGame?.servedTo?.radish!!
            9 -> session.currentGame?.servedTo?.temple = !session.currentGame?.servedTo?.temple!!
            10 -> session.currentGame?.servedTo?.pig = !session.currentGame?.servedTo?.pig!!
            11 -> session.currentGame?.servedTo?.moose = !session.currentGame?.servedTo?.moose!!
            12 -> session.currentGame?.servedTo?.shrimp = !session.currentGame?.servedTo?.shrimp!!
            13 -> session.currentGame?.servedTo?.sheep = !session.currentGame?.servedTo?.sheep!!
            else -> {
                // Handle the case where 'btnIndex' is out of expected range
                print("Error")
            }
        }
    }

    private fun updateServeImageButton(btnIndex: Int) {
        // Check if the provided 'btnIndex' is within the range of available buttons
        if (btnIndex in 0 until serveButtons.size) {
            val serveButton = serveButtons[btnIndex]

            // Determine whether the corresponding item is chosen or not based on 'btnIndex'
            val isItemChosen = when (btnIndex) {
                0 -> session.currentGame?.servedTo?.bbq
                1 -> session.currentGame?.servedTo?.bird
                2 -> session.currentGame?.servedTo?.cheese
                3 -> session.currentGame?.servedTo?.cow
                4 -> session.currentGame?.servedTo?.chili
                5 -> session.currentGame?.servedTo?.dessert
                6 -> session.currentGame?.servedTo?.fish
                7 -> session.currentGame?.servedTo?.glass
                8 -> session.currentGame?.servedTo?.radish
                9 -> session.currentGame?.servedTo?.temple
                10 -> session.currentGame?.servedTo?.pig
                11 -> session.currentGame?.servedTo?.moose
                12 -> session.currentGame?.servedTo?.shrimp
                13 -> session.currentGame?.servedTo?.sheep
                else -> {
                    // Handle the case where 'btnIndex' is out of the expected range
                    print("Error")
                    return
                }
            }

            // Determine the background color based on whether the item is served or not
            val backgroundColor = if (isItemChosen == true) {
                resources.getColor(R.color.green)
            } else {
                resources.getColor(R.color.black)
            }

            // Set the background color of the 'serveButton' based on the above determination
            serveButton.backgroundTintList = ColorStateList.valueOf(backgroundColor)
        } else {
            // Handle the case where 'btnIndex' is out of the expected range
            print("Error")
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