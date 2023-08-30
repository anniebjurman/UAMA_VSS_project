package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flavours(
    var bitter: Int = 0,
    var fullness: Int = 0,
    var sweetness: Int = 0
) : Parcelable