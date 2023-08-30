package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServedTo(
    val bbq: Boolean = false,
    val bird: Boolean = false,
    val cheese: Boolean = false,
    val chili: Boolean = false,
    val cow: Boolean = false,
    val dessert: Boolean = false,
    val fish: Boolean = false,
    val glass: Boolean = false,
    val moose: Boolean = false,
    val pig: Boolean = false,
    val radish: Boolean = false,
    val sheep: Boolean = false,
    val shrimp: Boolean = false,
    val temple: Boolean = false
) : Parcelable