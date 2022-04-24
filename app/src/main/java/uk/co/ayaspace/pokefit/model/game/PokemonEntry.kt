package uk.co.ayaspace.pokefit.model.game

import uk.co.ayaspace.pokefit.model.pokemon.PokemonSpecies
import com.google.gson.Gson

class PokemonEntry {
    // The index of this Pokémon species entry within the Pokédex.
    var entryNumber = 0
        private set

    // The Pokémon species being encountered.
    private var pokemon_species: PokemonSpecies? = null
    fun setEntryNumber(entry_number: Int): PokemonEntry {
        entryNumber = entry_number
        return this
    }

    val pokemonSpecies: PokemonSpecies?
        get() {
            if (!pokemon_species!!.isFetched) {
                pokemon_species = pokemon_species!!.get()
            }
            return pokemon_species
        }

    fun setPokemonSpecies(pokemon_species: PokemonSpecies?): PokemonEntry {
        this.pokemon_species = pokemon_species
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}