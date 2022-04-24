package uk.co.ayaspace.pokefit.model.move

import uk.co.ayaspace.pokefit.model.machine.MachineVersionDetail
import uk.co.ayaspace.pokefit.model.pokemon.Type
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class Move : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The percent value of how likely this move is to be successful.
    var accuracy = 0
        private set

    // The percent value of how likely it is this moves effect will happen.
    var effectChance = 0
        private set

    // Power points. The number of times this move can be used.
    var pp = 0
        private set

    // A value between -8 and 8. Sets the order in which moves are executed during battle. See Bulbapedia for greater detail.
    var priority = 0
        private set

    // The base power of this move with a value of 0 if it does not have a base power.
    var power = 0
        private set

    // The type of damage the move inflicts on the target, e.g. physical.
    private var damage_class: MoveDamageClass? = null

    // A list of the machines that teach this move.
    var machines: ArrayList<MachineVersionDetail>? = null
        private set

    // Metadata about this move
    var meta: MoveMetaData? = null
        private set

    // The name of this resource listed in different languages.
    var names: ArrayList<Name>? = null
        private set

    // The elemental type of this move.
    private var type: Type? = null
    fun setId(id: Int): Move {
        this.id = id
        return this
    }

    fun setAccuracy(accuracy: Int): Move {
        this.accuracy = accuracy
        return this
    }

    fun setEffectChance(effect_chance: Int): Move {
        effectChance = effect_chance
        return this
    }

    fun setPp(pp: Int): Move {
        this.pp = pp
        return this
    }

    fun setPriority(priority: Int): Move {
        this.priority = priority
        return this
    }

    fun setPower(power: Int): Move {
        this.power = power
        return this
    }

    val damageClass: MoveDamageClass?
        get() {
            if (!damage_class!!.isFetched) {
                damage_class = damage_class!!.get()
            }
            return damage_class
        }

    fun setDamageClass(damage_class: MoveDamageClass?): Move {
        this.damage_class = damage_class
        return this
    }

    fun setMachines(machines: ArrayList<MachineVersionDetail>?): Move {
        this.machines = machines
        return this
    }

    fun setMeta(meta: MoveMetaData?): Move {
        this.meta = meta
        return this
    }

    fun setNames(names: ArrayList<Name>?): Move {
        this.names = names
        return this
    }

    fun getType(): Type? {
        if (!type!!.isFetched) {
            type = type!!.get()
        }
        return type
    }

    fun setType(type: Type?): Move {
        this.type = type
        return this
    }

    fun get(): Move {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): Move {
            val obj: Move = Gson().fromJson(Information().fromInternet(url), Move::class.java)
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("move", limit, offset)
        }

        fun getById(id: Int): Move {
            return Companion["https://pokeapi.co/api/v2/move/$id"]
        }

        fun getByName(name: String): Move {
            return Companion["https://pokeapi.co/api/v2/move/$name"]
        }
    }
}