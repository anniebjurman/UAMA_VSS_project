package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

/**
 * A class to save the state of/ manage the
 * "served to" icons.
 */
@Parcelize
class ServedTo(
    var bbq: Boolean = false,
    var bird: Boolean = false,
    var cheese: Boolean = false,
    var chili: Boolean = false,
    var cow: Boolean = false,
    var dessert: Boolean = false,
    var fish: Boolean = false,
    var glass: Boolean = false,
    var moose: Boolean = false,
    var pig: Boolean = false,
    var radish: Boolean = false,
    var sheep: Boolean = false,
    var shrimp: Boolean = false,
    var temple: Boolean = false
) : Parcelable {

    fun getChosenItems(): ArrayList<String> {
        // Create an ArrayList to store the chosen items
        val chosen = ArrayList<String>()

        // Check each item and add it to 'chosen' if it is selected
        if (bbq) chosen.add("bbq")
        if (bird) chosen.add("bird")
        if (cheese) chosen.add("cheese")
        if (chili) chosen.add("chili")
        if (cow) chosen.add("cow")
        if (dessert) chosen.add("dessert")
        if (fish) chosen.add("fish")
        if (glass) chosen.add("glass")
        if (moose) chosen.add("moose")
        if (pig) chosen.add("pig")
        if (radish) chosen.add("radish")
        if (sheep) chosen.add("sheep")
        if (shrimp) chosen.add("shrimp")
        if (temple) chosen.add("temple")

        return chosen
    }

    fun getIcon(icon: String): Int {
        // Return the corresponding drawable resource ID based on the provided 'icon' string
        return when (icon) {
            "bbq" -> R.drawable.bbq
            "bird" -> R.drawable.bird
            "cheese" -> R.drawable.cheese
            "chili" -> R.drawable.chili
            "cow" -> R.drawable.cow
            "dessert" -> R.drawable.dessert
            "fish" -> R.drawable.fish
            "glass" -> R.drawable.glas
            "moose" -> R.drawable.moose
            "pig" -> R.drawable.pig
            "radish" -> R.drawable.radish
            "sheep" -> R.drawable.sheep
            "shrimp" -> R.drawable.shrimp
            "temple" -> R.drawable.temple
            else -> R.drawable.beer // Return a default drawable if no matching icon is found
        }
    }

    fun toggleChosenIcon(icon: String) {
        when (icon) {
            "bbq" -> bbq = !bbq
            "bird" -> bird = !bird
            "cheese" -> cheese = !cheese
            "chili" -> chili = !chili
            "cow" -> cow = !cow
            "dessert" -> dessert = !dessert
            "fish" -> fish = !fish
            "glass" -> glass = !glass
            "moose" -> moose = !moose
            "pig" -> pig = !pig
            "radish" -> radish = !radish
            "sheep" -> sheep = !sheep
            "shrimp" -> shrimp = !shrimp
            "temple" -> temple = !temple
            else -> {
                Log.e("ServedTo.kt", "chosen icon does not exist")
            }
        }
    }

}