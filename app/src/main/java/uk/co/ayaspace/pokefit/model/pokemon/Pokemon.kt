package uk.co.ayaspace.pokefit.model.pokemon

import com.google.gson.Gson
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information

class Pokemon : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The base experience gained for defeating this Pokémon.
    var baseExperience = 0
        private set

    // The height of this Pokémon in decimetres.
    var height = 0
        private set

    // Set for exactly one Pokémon used as the default for each species.
    var isDefault = false
        private set

    // Order for sorting. Almost national order, except families are grouped together.
    var order = 0
        private set

    // The weight of this Pokémon in hectograms.
    var weight = 0
        private set

    // A list of abilities this Pokémon could potentially have.
    var abilities: ArrayList<PokemonAbility>? = null

    // A link to a list of location areas, as well as encounter details pertaining to specific versions.
    var locationAreaEncounters: String? = null
        private set

    // A list of moves along with learn methods and level details pertaining to specific version groups.
    private var moves: ArrayList<PokemonMove>? = null

    // A set of sprites used to depict this Pokémon in the game. A visual representation of the various sprites can be found at PokeAPI/sprites
    private var sprites: PokemonSprites? = null

    // The species this Pokémon belongs to.
    var species: PokemonSpecies? = null

    // A list of base stat values for this Pokémon.
    var stats: ArrayList<PokemonStat>? = null

    // A list of details showing types this Pokémon has.
    var types: ArrayList<PokemonType>? = null
    fun setId(id: Int): Pokemon {
        this.id = id
        return this
    }

    fun setBaseExperience(base_experience: Int): Pokemon {
        baseExperience = base_experience
        return this
    }

    fun setHeight(height: Int): Pokemon {
        this.height = height
        return this
    }

    fun setIsDefault(is_default: Boolean): Pokemon {
        isDefault = is_default
        return this
    }

    fun setOrder(order: Int): Pokemon {
        this.order = order
        return this
    }

    fun setWeight(weight: Int): Pokemon {
        this.weight = weight
        return this
    }

    fun getAllAbilities(): ArrayList<PokemonAbility>? {
        return abilities
    }

    fun setAbilities(abilities: ArrayList<PokemonAbility>?): Pokemon {
        this.abilities = abilities
        return this
    }

    fun setLocationAreaEncounters(location_area_encounters: String?): Pokemon {
        locationAreaEncounters = location_area_encounters
        return this
    }

    fun getMoves(): ArrayList<PokemonMove>? {
        return moves
    }

    fun setMoves(moves: ArrayList<PokemonMove>?): Pokemon {
        this.moves = moves
        return this
    }

    fun getSprites(): PokemonSprites? {
        return sprites
    }

    fun setSprites(sprites: PokemonSprites?): Pokemon {
        this.sprites = sprites
        return this
    }

    fun get(): Pokemon {
        return Companion[this.url!!]
    }

    companion object {
        private operator fun get(url: String): Pokemon {
            val str: String = Information().fromInternet(url)
            val obj: Pokemon = Gson().fromJson(Information().fromInternet(url), Pokemon::class.java)
            obj.setIsFetched(true)
            System.out.println(obj)
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("pokemon", limit, offset)
        }

        fun getById(id: Int): Pokemon {
            val p = Companion["https://pokeapi.co/api/v2/pokemon/$id"]
            return p
        }

        fun getByName(name: String): Pokemon {
            return Companion["https://pokeapi.co/api/v2/pokemon/$name"]
        }
    }
}