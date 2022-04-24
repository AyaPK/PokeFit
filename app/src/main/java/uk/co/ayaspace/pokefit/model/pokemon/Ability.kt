package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class Ability : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // Whether or not this ability originated in the main series of the video games.
    var isMainSeries = false
        private set

    // A list of Pokémon that could potentially have this ability.
    var pokemon: ArrayList<AbilityPokemon>? = null
        private set

    fun setId(id: Int): Ability {
        this.id = id
        return this
    }

    fun setIsMainSeries(is_main_series: Boolean): Ability {
        isMainSeries = is_main_series
        return this
    }

    fun setPokemon(pokemon: ArrayList<AbilityPokemon>?): Ability {
        this.pokemon = pokemon
        return this
    }

    fun get(): Ability {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): Ability {
            val obj: Ability = Gson().fromJson(Information().fromInternet(url), Ability::class.java)
            obj.isFetched = true
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("ability", limit, offset)
        }

        fun getById(id: Int): Ability {
            return Companion["https://pokeapi.co/api/v2/ability/$id"]
        }

        fun getByName(name: String): Ability {
            return Companion["https://pokeapi.co/api/v2/ability/$name"]
        }
    }
}