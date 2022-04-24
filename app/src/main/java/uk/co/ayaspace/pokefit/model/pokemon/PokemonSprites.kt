package uk.co.ayaspace.pokefit.model.pokemon

import com.google.gson.Gson

class PokemonSprites {
    // The default depiction of this Pokémon from the front in battle.
    var frontDefault: String? = null
        private set

    // The shiny depiction of this Pokémon from the front in battle.
    var frontShiny: String? = null
        private set

    // The female depiction of this Pokémon from the front in battle.
    var frontFemale: String? = null
        private set

    // The shiny female depiction of this Pokémon from the front in battle.
    var frontShinyFemale: String? = null
        private set

    // The default depiction of this Pokémon from the back in battle.
    var backDefault: String? = null
        private set

    // The shiny depiction of this Pokémon from the back in battle.
    var backShiny: String? = null
        private set

    // The female depiction of this Pokémon from the back in battle.
    var backFemale: String? = null
        private set

    // The shiny female depiction of this Pokémon from the back in battle.
    var backShinyFemale: String? = null
        private set

    fun setFrontDefault(front_default: String?): PokemonSprites {
        frontDefault = front_default
        return this
    }

    fun setFrontShiny(front_shiny: String?): PokemonSprites {
        frontShiny = front_shiny
        return this
    }

    fun setFrontFemale(front_female: String?): PokemonSprites {
        frontFemale = front_female
        return this
    }

    fun setFrontShinyFemale(front_shiny_female: String?): PokemonSprites {
        frontShinyFemale = front_shiny_female
        return this
    }

    fun setBackDefault(back_default: String?): PokemonSprites {
        backDefault = back_default
        return this
    }

    fun setBackShiny(back_shiny: String?): PokemonSprites {
        backShiny = back_shiny
        return this
    }

    fun setBackFemale(back_female: String?): PokemonSprites {
        backFemale = back_female
        return this
    }

    fun setBackShinyFemale(back_shiny_female: String?): PokemonSprites {
        backShinyFemale = back_shiny_female
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}