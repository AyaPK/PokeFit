package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.move.Move
import com.google.gson.Gson

class PokemonMove {
    // The move the Pokémon can learn.
    private var move: Move? = null
    fun getMove(): Move? {
        if (!move!!.isFetched) {
            move = move!!.get()
        }
        return move
    }

    fun setMove(move: Move?): PokemonMove {
        this.move = move
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}