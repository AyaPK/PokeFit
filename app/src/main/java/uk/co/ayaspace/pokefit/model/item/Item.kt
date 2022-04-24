package uk.co.ayaspace.pokefit.model.item

import uk.co.ayaspace.pokefit.utils.Information
import uk.co.ayaspace.pokefit.model.evolution.EvolutionChain
import uk.co.ayaspace.pokefit.model.machine.MachineVersionDetail
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import com.google.gson.Gson

class Item : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The name of this item listed in different languages.
    var names: ArrayList<Name>? = null
        private set

    // A set of sprites used to depict this item in the game.
    var sprites: ItemSprites? = null
        private set

    // An evolution chain this item requires to produce a bay during mating.
    private var baby_trigger_for: EvolutionChain? = null

    // A list of the machines related to this item.
    var machines: ArrayList<MachineVersionDetail>? = null
        private set

    fun setId(id: Int): Item {
        this.id = id
        return this
    }

    fun setNames(names: ArrayList<Name>?): Item {
        this.names = names
        return this
    }

    fun setSprites(sprites: ItemSprites?): Item {
        this.sprites = sprites
        return this
    }

    val babyTriggerFor: EvolutionChain?
        get() {
            if (!baby_trigger_for!!.isFetched) {
                baby_trigger_for = baby_trigger_for!!.get()
            }
            return baby_trigger_for
        }

    fun setBabyTriggerFor(baby_trigger_for: EvolutionChain?): Item {
        this.baby_trigger_for = baby_trigger_for
        return this
    }

    fun setMachines(machines: ArrayList<MachineVersionDetail>?): Item {
        this.machines = machines
        return this
    }

    companion object {
        private operator fun get(url: String): Item {
            val obj: Item = Gson().fromJson(Information().fromInternet(url), Item::class.java)
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("item", limit, offset)
        }

        fun getById(id: Int): Item {
            return Companion["https://pokeapi.co/api/v2/item/$id"]
        }

        fun getByName(name: String): Item {
            return Companion["https://pokeapi.co/api/v2/item/$name"]
        }
    }
}