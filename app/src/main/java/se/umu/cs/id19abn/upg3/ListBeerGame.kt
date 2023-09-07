package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A data class representing a list of beerGames
 */
@Parcelize
data class ListBeerGame(
    var beerGames: ArrayList<BeerGame> = ArrayList()
) :Parcelable