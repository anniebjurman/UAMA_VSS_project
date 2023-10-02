package se.umu.cs.id19abn.upg3

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * A class to keep track of who is logged in and
 * manage
 */
@Parcelize
class Session(
    var dbHelper: DbHelper? = null,
    var user: String? = null,
    var currentGame: BeerGame? = null
): Parcelable {


}