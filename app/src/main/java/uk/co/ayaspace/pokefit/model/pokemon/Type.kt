package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.move.Move
import uk.co.ayaspace.pokefit.model.move.MoveDamageClass
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class Type : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The class of damage inflicted by this type.
    private var move_damage_class: MoveDamageClass? = null

    // The name of this resource listed in different languages.
    var names: ArrayList<Name>? = null
        private set

    // A list of details of Pokémon that have this type.
    var pokemon: ArrayList<TypePokemon>? = null
        private set

    // A list of moves that have this type.
    var moves: ArrayList<Move>? = null
        private set

    fun setId(id: Int): Type {
        this.id = id
        return this
    }

    val moveDamageClass: MoveDamageClass?
        get() {
            if (!move_damage_class!!.isFetched) {
                move_damage_class = move_damage_class!!.get()
            }
            return move_damage_class
        }

    fun setMoveDamageClass(move_damage_class: MoveDamageClass?): Type {
        this.move_damage_class = move_damage_class
        return this
    }

    fun setNames(names: ArrayList<Name>?): Type {
        this.names = names
        return this
    }

    fun setPokemon(pokemon: ArrayList<TypePokemon>?): Type {
        this.pokemon = pokemon
        return this
    }

    fun setMoves(moves: ArrayList<Move>?): Type {
        this.moves = moves
        return this
    }

    fun get(): Type {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): Type {
            val obj: Type = Gson().fromJson(Information().fromInternet(url), Type::class.java)
            obj.isFetched = true
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("type", limit, offset)
        }

        fun getById(id: Int): Type {
            return Companion["https://pokeapi.co/api/v2/type/$id"]
        }

        fun getByName(name: String): Type {
            return Companion["https://pokeapi.co/api/v2/type/$name"]
        }
    }
}