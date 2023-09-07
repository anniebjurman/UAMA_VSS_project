package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A Data Class representing use input for analysing a beer.
 */
@Parcelize
data class BeerGame(
    var imgPath: String? = null,
    var beerName: String? = null,
    var flavours: Flavours = Flavours(),
    var servedTo: ServedTo = ServedTo(),
    var describedAs: MutableMap<Int, String> = mutableMapOf()
) : Parcelable
