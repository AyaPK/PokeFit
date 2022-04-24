package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class EggGroup : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0

    // The name of this resource listed in different languages.
    var names: ArrayList<Name>? = null

    // A list of all Pokémon species that are members of this egg group.
    var pokemonSpecies: ArrayList<PokemonSpecies>? = null

    fun setId(id: Int): EggGroup {
        this.id = id
        return this
    }

    fun setNames(names: ArrayList<Name>?): EggGroup {
        this.names = names
        return this
    }

    fun setPokemonSpecies(pokemon_species: ArrayList<PokemonSpecies>?): EggGroup {
        pokemonSpecies = pokemon_species
        return this
    }

    fun get(): EggGroup {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): EggGroup {
            val obj: EggGroup = Gson().fromJson(Information().fromInternet(url), EggGroup::class.java)
            obj.isFetched = true
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("egg-group", limit, offset)
        }

        fun getById(id: Int): EggGroup {
            return Companion["https://pokeapi.co/api/v2/egg-group/$id"]
        }

        fun getByName(name: String): EggGroup {
            return Companion["https://pokeapi.co/api/v2/egg-group/$name"]
        }
    }
}