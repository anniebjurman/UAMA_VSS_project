package se.umu.cs.id19abn.upg3

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BeerGame(
    var imgPath: String? = null,
    var beerName: String? = null,
    var flavours: Flavours = Flavours(),
    var servedTo: ServedTo = ServedTo(),
    var describedAs: MutableMap<Int, String> = mutableMapOf()
) : Parcelable
