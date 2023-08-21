package se.umu.cs.id19abn.upg3

data class GameBeer(
    var beerName: String? = null,
    var bitter: Int? = null,
    var fullness: Int? = null,
    var sweetness: Int? = null,
    var servedTo: ArrayList<String>? = null,
    var describedAs: ArrayList<String>? = null)
