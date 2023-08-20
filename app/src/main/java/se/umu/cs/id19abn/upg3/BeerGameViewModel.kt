package se.umu.cs.id19abn.upg3

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BeerGameViewModel : ViewModel() {
    // Expose screen UI state
    private val _beerGame = MutableStateFlow(GameBeer())
    val beerGame: StateFlow<GameBeer> = _beerGame.asStateFlow()

    fun updateBeerName(name: String) {
        _beerGame.update { currentState ->
            currentState.copy(
                beerName = name
            )
        }
    }

    fun updateBitter(num: Int) {
        _beerGame.update { currentState ->
            currentState.copy(
                bitter = num
            )
        }
    }

    fun updateFullness(num: Int) {
        _beerGame.update { currentState ->
            currentState.copy(
                fullness = num
            )
        }
    }

    fun updateServedTo(list: ArrayList<String>) {
        _beerGame.update { currentState ->
            currentState.copy(
                servedTo = list
            )
        }
    }

    fun updateDescribedAs(list: ArrayList<String>) {
        _beerGame.update { currentState ->
            currentState.copy(
                describedAs = list
            )
        }
    }
}