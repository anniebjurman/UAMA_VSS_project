package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListBeerGame(
    var beerGames: ArrayList<BeerGame> = ArrayList()
) :Parcelable