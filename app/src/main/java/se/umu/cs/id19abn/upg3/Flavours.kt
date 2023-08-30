package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flavours(
    val bitter: Int = 0,
    val fullness: Int = 0,
    var sweetness: Int = 0
) : Parcelable