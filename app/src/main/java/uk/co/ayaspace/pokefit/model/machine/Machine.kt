package uk.co.ayaspace.pokefit.model.machine

import uk.co.ayaspace.pokefit.utils.Information
import uk.co.ayaspace.pokefit.model.item.Item
import uk.co.ayaspace.pokefit.model.move.Move
import uk.co.ayaspace.pokefit.model.utility.APIResource
import com.google.gson.Gson

class Machine : APIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The TM or HM item that corresponds to this machine.
    private var item: Item? = null

    // The move that is taught by this machine.
    private var move: Move? = null
    fun setId(id: Int): Machine {
        this.id = id
        return this
    }

    fun getItem(): Item? {
        return item
    }

    fun setItem(item: Item?): Machine {
        this.item = item
        return this
    }

    fun getMove(): Move? {
        if (!move!!.isFetched) {
            move = move!!.get()
        }
        return move
    }

    fun setMove(move: Move?): Machine {
        this.move = move
        return this
    }


    companion object {
        private operator fun get(url: String): Machine {
            val obj: Machine = Gson().fromJson(Information().fromInternet(url), Machine::class.java)
            return obj
        }

        fun getById(id: Int): Machine {
            return Companion["https://pokeapi.co/api/v2/machine/$id"]
        }
    }
}