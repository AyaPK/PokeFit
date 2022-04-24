package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.game.Pokedex
import com.google.gson.Gson

class PokemonSpeciesDexEntry {
    // The index number within the Pokédex.
    var entryNumber = 0
        private set

    // The Pokédex the referenced Pokémon species can be found in.
    private var pokedex: Pokedex? = null
    fun setEntryNumber(entry_number: Int): PokemonSpeciesDexEntry {
        entryNumber = entry_number
        return this
    }

    fun getPokedex(): Pokedex? {
        return pokedex
    }

    fun setPokedex(pokedex: Pokedex?): PokemonSpeciesDexEntry {
        this.pokedex = pokedex
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}