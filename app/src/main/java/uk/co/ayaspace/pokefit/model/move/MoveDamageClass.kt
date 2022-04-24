package uk.co.ayaspace.pokefit.model.move

import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Description
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class MoveDamageClass : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The description of this resource listed in different languages.
    var descriptions: ArrayList<Description>? = null
        private set

    // A list of moves that fall into this damage class.
    var moves: ArrayList<Move>? = null
        private set

    // The name of this resource listed in different languages.
    var names: ArrayList<Name>? = null
        private set

    fun setId(id: Int): MoveDamageClass {
        this.id = id
        return this
    }

    fun setDescriptions(descriptions: ArrayList<Description>?): MoveDamageClass {
        this.descriptions = descriptions
        return this
    }

    fun setMoves(moves: ArrayList<Move>?): MoveDamageClass {
        this.moves = moves
        return this
    }

    fun setNames(names: ArrayList<Name>?): MoveDamageClass {
        this.names = names
        return this
    }

    fun get(): MoveDamageClass {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): MoveDamageClass {
            val obj: MoveDamageClass = Gson().fromJson(Information().fromInternet(url), MoveDamageClass::class.java)
            obj.isFetched = true
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("move-damage-class", limit, offset)
        }

        fun getById(id: Int): MoveDamageClass {
            return Companion["https://pokeapi.co/api/v2/move-damage-class/$id"]
        }

        fun getByName(name: String): MoveDamageClass {
            return Companion["https://pokeapi.co/api/v2/move-damage-class/$name"]
        }
    }
}