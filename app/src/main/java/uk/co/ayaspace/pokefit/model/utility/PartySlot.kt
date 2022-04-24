package uk.co.ayaspace.pokefit.model.utility

open class PartySlot(var name: String?) {
    private val id: Double

    init {
        val x = Math.random() * 99999
        val y = Math.random() * 99999
        id = x * y
    }
}