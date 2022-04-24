package uk.co.ayaspace.pokefit.model.evolution

import uk.co.ayaspace.pokefit.utils.Information
import uk.co.ayaspace.pokefit.model.item.Item
import uk.co.ayaspace.pokefit.model.utility.APIResource
import com.google.gson.Gson

class EvolutionChain : APIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The item that a Pokémon would be holding when mating that would trigger the egg hatching a baby Pokémon rather than a basic Pokémon.
    private var baby_trigger_item: Item? = null

    // The base chain link object. Each link contains evolution details for a Pokémon in the chain. Each link references the next Pokémon in the natural evolution order.
    var chain: ChainLink? = null
        private set

    fun setId(id: Int): EvolutionChain {
        this.id = id
        return this
    }

    val babyTriggerItem: Item?
        get() {
            return baby_trigger_item
        }

    fun setBabyTriggerItem(baby_trigger_item: Item?): EvolutionChain {
        this.baby_trigger_item = baby_trigger_item
        return this
    }

    fun setChain(chain: ChainLink?): EvolutionChain {
        this.chain = chain
        return this
    }

    fun get(): EvolutionChain {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): EvolutionChain {
            val obj = Gson().fromJson(Information().fromInternet(url), EvolutionChain::class.java)
            return obj
        }

        fun getById(id: Int): EvolutionChain {
            return Companion["https://pokeapi.co/api/v2/evolution-chain/$id"]
        }
    }
}