package uk.co.ayaspace.pokefit.model.machine

import com.google.gson.Gson

class MachineVersionDetail {
    // The machine that teaches a move from an item.
    private var machine: Machine? = null
    fun getMachine(): Machine? {
        return machine
    }

    fun setMachine(machine: Machine?): MachineVersionDetail {
        this.machine = machine
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}