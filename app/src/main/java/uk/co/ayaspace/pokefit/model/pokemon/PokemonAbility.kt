package uk.co.ayaspace.pokefit.model.pokemon

import com.google.gson.Gson

class PokemonAbility {
    // Whether or not this is a hidden ability.
    var isHidden = false
        private set

    // The slot this ability occupies in this Pokémon species.
    var slot = 0
        private set

    // The ability the Pokémon may have.
    private var ability: Ability? = null
    fun setIsHidden(is_hidden: Boolean): PokemonAbility {
        isHidden = is_hidden
        return this
    }

    fun setSlot(slot: Int): PokemonAbility {
        this.slot = slot
        return this
    }

    fun getAbility(): Ability? {
        if (!ability!!.isFetched) {
            ability = ability!!.get()
        }
        return ability
    }

    fun setAbility(ability: Ability?): PokemonAbility {
        this.ability = ability
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}