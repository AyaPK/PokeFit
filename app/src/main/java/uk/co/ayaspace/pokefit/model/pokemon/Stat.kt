package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.move.MoveDamageClass
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class Stat : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // ID the games use for this stat.
    var gameIndex = 0
        private set

    // Whether this stat only exists within a battle.
    var isBattleOnly = false
        private set

    // The class of damage this stat is directly related to.
    private var move_damage_class: MoveDamageClass? = null

    // The name of this resource listed in different languages.
    var names: ArrayList<Name>? = null
        private set

    fun setId(id: Int): Stat {
        this.id = id
        return this
    }

    fun setGameIndex(game_index: Int): Stat {
        gameIndex = game_index
        return this
    }

    fun setIsBattleOnly(is_battle_only: Boolean): Stat {
        isBattleOnly = is_battle_only
        return this
    }

    val moveDamageClass: MoveDamageClass?
        get() {
            if (!move_damage_class!!.isFetched) {
                move_damage_class = move_damage_class!!.get()
            }
            return move_damage_class
        }

    fun setMoveDamageClass(move_damage_class: MoveDamageClass?): Stat {
        this.move_damage_class = move_damage_class
        return this
    }

    fun setNames(names: ArrayList<Name>?): Stat {
        this.names = names
        return this
    }

    fun get(): Stat {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): Stat {
            val obj: Stat = Gson().fromJson(Information().fromInternet(url), Stat::class.java)
            obj.isFetched = true
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("stat", limit, offset)
        }

        fun getById(id: Int): Stat {
            return Companion["https://pokeapi.co/api/v2/stat/$id"]
        }

        fun getByName(name: String): Stat {
            return Companion["https://pokeapi.co/api/v2/stat/$name"]
        }
    }
}