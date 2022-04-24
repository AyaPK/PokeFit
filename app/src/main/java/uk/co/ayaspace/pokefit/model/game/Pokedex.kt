package uk.co.ayaspace.pokefit.model.game

import uk.co.ayaspace.pokefit.utils.Information
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import com.google.gson.Gson

class Pokedex : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // Whether or not this Pokédex originated in the main series of the video games.
    var isMainSeries = false
        private set

    // The name of this resource listed in different languages.
    var names: ArrayList<Name>? = null
        private set

    // A list of Pokémon catalogued in this Pokédex and their indexes.
    var pokemonEntries: ArrayList<PokemonEntry>? = null
        private set

    fun setId(id: Int): Pokedex {
        this.id = id
        return this
    }

    fun setIsMainSeries(is_main_series: Boolean): Pokedex {
        isMainSeries = is_main_series
        return this
    }

    fun setNames(names: ArrayList<Name>?): Pokedex {
        this.names = names
        return this
    }

    fun setPokemonEntries(pokemon_entries: ArrayList<PokemonEntry>?): Pokedex {
        pokemonEntries = pokemon_entries
        return this
    }

    companion object {
        private operator fun get(url: String): Pokedex {
            val obj: Pokedex = Gson().fromJson(Information().fromInternet(url), Pokedex::class.java)
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("pokedex", limit, offset)
        }

        fun getById(id: Int): Pokedex {
            return Companion["https://pokeapi.co/api/v2/pokedex/$id"]
        }

        fun getByName(name: String): Pokedex {
            return Companion["https://pokeapi.co/api/v2/pokedex/$name"]
        }
    }
}