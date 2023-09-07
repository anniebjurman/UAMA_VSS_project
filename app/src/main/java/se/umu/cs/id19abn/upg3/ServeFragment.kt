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
    private lateinit var beerGameObj: BeerGame
    private var serveButtons: ArrayList<ImageButton> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the 'beerGameObj' from the previous fragment's arguments
        beerGameObj = arguments?.let { ServeFragmentArgs.fromBundle(it).beerGame }!!
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
                ServeFragmentDirections.actionServeFragmentToDescriptionFragment(beerGameObj)
            binding.root.findNavController().navigate(action)
        }

        // Return the root view of the inflated layout
        return binding.root
    }

//    private fun setExistingData() {
//        val chosenItems: ArrayList<String> = beerGameObj.servedTo.getChosenItems()
//        chosenItems.forEach {item ->
//            if (item == "bbq") {
//                updateServeImageButton(0)
//            }
//            if (item == "bird") {
//                updateServeImageButton(1)
//            }
//            if (item == "cheese") {
//                updateServeImageButton(2)
//            }
//            if (item == "cow") {
//                updateServeImageButton(3)
//            }
//            if (item == "chili") {
//                updateServeImageButton(4)
//            }
//            if (item == "dessert") {
//                updateServeImageButton(5)
//            }
//            if (item == "fish") {
//                updateServeImageButton(6)
//            }
//            if (item == "glass") {
//                updateServeImageButton(7)
//            }
//            if (item == "radish") {
//                updateServeImageButton(8)
//            }
//            if (item == "temple") {
//                updateServeImageButton(9)
//            }
//            if (item == "pig") {
//                updateServeImageButton(10)
//            }
//            if (item == "moose") {
//                updateServeImageButton(11)
//            }
//            if (item == "shrimp") {
//                updateServeImageButton(12)
//            }
//            if (item == "sheep") {
//                updateServeImageButton(13)
//            }
//        }
//    }

    private fun setExistingData() {
        val chosenItems: ArrayList<String> = beerGameObj.servedTo.getChosenItems()

        // Create a map to map item names to their corresponding button indices
        val itemToButtonIndex = mapOf(
            "bbq" to 0, "bird" to 1, "cheese" to 2, "cow" to 3, "chili" to 4,
            "dessert" to 5, "fish" to 6, "glass" to 7, "radish" to 8,
            "temple" to 9, "pig" to 10, "moose" to 11, "shrimp" to 12, "sheep" to 13
        )

        chosenItems.forEach { item ->
            // Check if the item is mapped to a button index and call 'updateServeImageButton'
            itemToButtonIndex[item]?.let { buttonIndex ->
                updateServeImageButton(buttonIndex)
            }
        }
    }

    private fun toggleServeButton(btnIndex: Int) {
        // Toggle properties in 'servedTo' of 'beerGameObj'
        when (btnIndex) {
            0 -> beerGameObj.servedTo.bbq = !beerGameObj.servedTo.bbq
            1 -> beerGameObj.servedTo.bird = !beerGameObj.servedTo.bird
            2 -> beerGameObj.servedTo.cheese = !beerGameObj.servedTo.cheese
            3 -> beerGameObj.servedTo.cow = !beerGameObj.servedTo.cow
            4 -> beerGameObj.servedTo.chili = !beerGameObj.servedTo.chili
            5 -> beerGameObj.servedTo.dessert = !beerGameObj.servedTo.dessert
            6 -> beerGameObj.servedTo.fish = !beerGameObj.servedTo.fish
            7 -> beerGameObj.servedTo.glass = !beerGameObj.servedTo.glass
            8 -> beerGameObj.servedTo.radish = !beerGameObj.servedTo.radish
            9 -> beerGameObj.servedTo.temple = !beerGameObj.servedTo.temple
            10 -> beerGameObj.servedTo.pig = !beerGameObj.servedTo.pig
            11 -> beerGameObj.servedTo.moose = !beerGameObj.servedTo.moose
            12 -> beerGameObj.servedTo.shrimp = !beerGameObj.servedTo.shrimp
            13 -> beerGameObj.servedTo.sheep = !beerGameObj.servedTo.sheep
            else -> {
                // Handle the case where 'btnIndex' is out of expected range
                print("Error")
            }
        }
    }

//    private fun updateServeImageButton(btnIndex: Int) {
//        when (btnIndex) {
//            0 -> {
//                if (beerGameObj.servedTo.bbq) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            1 -> {
//                if (beerGameObj.servedTo.bird) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            2 -> {
//                if (beerGameObj.servedTo.cheese) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            3 -> {
//                if (beerGameObj.servedTo.cow) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            4 -> {
//                if (beerGameObj.servedTo.chili) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            5 -> {
//                if (beerGameObj.servedTo.dessert) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            6 -> {
//                if (beerGameObj.servedTo.fish) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            7 -> {
//                if (beerGameObj.servedTo.glass) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            8 -> {
//                if (beerGameObj.servedTo.radish) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            9 -> {
//                if (beerGameObj.servedTo.temple) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            10 -> {
//                if (beerGameObj.servedTo.pig) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            11 -> {
//                if (beerGameObj.servedTo.moose) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            12 -> {
//                if (beerGameObj.servedTo.shrimp) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            13 -> {
//                if (beerGameObj.servedTo.sheep) {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.green));
//                } else {
//                    serveButtons[btnIndex].backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black));
//                }
//            }
//            else -> {
//                print("Error")
//            }
//        }
//    }

    private fun updateServeImageButton(btnIndex: Int) {
        // Check if the provided 'btnIndex' is within the range of available buttons
        if (btnIndex in 0 until serveButtons.size) {
            val serveButton = serveButtons[btnIndex]

            // Determine whether the corresponding item is chosen or not based on 'btnIndex'
            val isItemChosen = when (btnIndex) {
                0 -> beerGameObj.servedTo.bbq
                1 -> beerGameObj.servedTo.bird
                2 -> beerGameObj.servedTo.cheese
                3 -> beerGameObj.servedTo.cow
                4 -> beerGameObj.servedTo.chili
                5 -> beerGameObj.servedTo.dessert
                6 -> beerGameObj.servedTo.fish
                7 -> beerGameObj.servedTo.glass
                8 -> beerGameObj.servedTo.radish
                9 -> beerGameObj.servedTo.temple
                10 -> beerGameObj.servedTo.pig
                11 -> beerGameObj.servedTo.moose
                12 -> beerGameObj.servedTo.shrimp
                13 -> beerGameObj.servedTo.sheep
                else -> {
                    // Handle the case where 'btnIndex' is out of the expected range
                    print("Error")
                    return
                }
            }

            // Determine the background color based on whether the item is served or not
            val backgroundColor = if (isItemChosen) {
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