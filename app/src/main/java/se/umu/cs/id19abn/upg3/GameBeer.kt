package se.umu.cs.id19abn.upg3

data class GameBeer(val beerName: String? = null,
                    val bitter: Int? = null,
                    val fullness: Int? = null,
                    val sweetness: Int? = null,
                    val servedTo: ArrayList<String>? = null,
                    val describedAs: ArrayList<String>? = null)
