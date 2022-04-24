package uk.co.ayaspace.pokefit.model.pokemon

import com.google.gson.Gson

class TypePokemon {
    // The order the Pokémon's types are listed in.
    var slot = 0
        private set

    // The Pokémon that has the referenced type.
    private var pokemon: Pokemon? = null
    fun setSlot(slot: Int): TypePokemon {
        this.slot = slot
        return this
    }

    fun getPokemon(): Pokemon? {
        if (!pokemon!!.isFetched) {
            pokemon = pokemon!!.get()
        }
        return pokemon
    }

    fun setPokemon(pokemon: Pokemon?): TypePokemon {
        this.pokemon = pokemon
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}