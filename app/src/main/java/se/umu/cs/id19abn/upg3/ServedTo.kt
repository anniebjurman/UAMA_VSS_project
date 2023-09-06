package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
        val chosen = ArrayList<String>()

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
            else -> R.drawable.beer
        }
    }

}