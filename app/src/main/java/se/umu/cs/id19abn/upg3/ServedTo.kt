package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServedTo(
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
) : Parcelable