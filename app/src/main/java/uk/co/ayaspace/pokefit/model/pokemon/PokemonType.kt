package uk.co.ayaspace.pokefit.model.pokemon

import com.google.gson.Gson

class PokemonType {
    // The order the Pokémon's types are listed in.
    var slot = 0
        private set

    // The type the referenced Pokémon has.
    private var type: Type? = null
    fun setSlot(slot: Int): PokemonType {
        this.slot = slot
        return this
    }

    fun getType(): Type? {
        if (!type!!.isFetched) {
            type = type!!.get()
        }
        return type
    }

    fun setType(type: Type?): PokemonType {
        this.type = type
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}