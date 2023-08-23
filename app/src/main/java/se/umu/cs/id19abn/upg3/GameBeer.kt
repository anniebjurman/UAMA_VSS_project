package se.umu.cs.id19abn.upg3

import android.net.Uri

data class GameBeer(
    var imgPath: Uri? = null,
    var beerName: String? = null,
    var flavours: ArrayList<Int>? = null,
    var servedTo: ArrayList<String>? = null,
    var describedAs: ArrayList<String>? = null)
