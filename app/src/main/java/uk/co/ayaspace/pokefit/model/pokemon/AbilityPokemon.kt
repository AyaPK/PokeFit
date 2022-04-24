package uk.co.ayaspace.pokefit.model.pokemon

import com.google.gson.Gson

class AbilityPokemon {
    // Whether or not this a hidden ability for the referenced Pokémon.
    var isHidden = false
        private set

    // Pokémon have 3 ability 'slots' which hold references to possible abilities they could have. This is the slot of this ability for the referenced pokemon.
    var slot = 0
        private set

    // The Pokémon this ability could belong to.
    private var pokemon: Pokemon? = null
    fun setIsHidden(is_hidden: Boolean): AbilityPokemon {
        isHidden = is_hidden
        return this
    }

    fun setSlot(slot: Int): AbilityPokemon {
        this.slot = slot
        return this
    }

    fun getPokemon(): Pokemon? {
        if (!pokemon!!.isFetched) {
            pokemon = pokemon!!.get()
        }
        return pokemon
    }

    fun setPokemon(pokemon: Pokemon?): AbilityPokemon {
        this.pokemon = pokemon
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}