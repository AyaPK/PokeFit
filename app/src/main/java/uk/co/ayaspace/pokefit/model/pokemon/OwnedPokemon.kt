package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.utility.PartySlot

class OwnedPokemon(
    name: String?, var ability: String?,
    atk: Int, def: Int, spatk: Int, spdef: Int, hp: Int, spd: Int,
    atkiv: Int, defiv: Int, spatkiv: Int, spdefiv: Int, hpiv: Int, spdiv: Int, pkid: Int,
    var mainType: String, var pokemonType: String, var species: String) : PartySlot(name) {
    private val stats: HashMap<String, Int>
    var ivs: HashMap<String, Int>
    var heldItem: String? = null
    var pokeid: Int = 0

    init {
        pokeid = pkid
        val statsHash = HashMap<String, Int>()
        statsHash["atk"] = atk
        statsHash["def"] = def
        statsHash["spatk"] = spatk
        statsHash["spdef"] = spdef
        statsHash["hp"] = hp
        statsHash["spd"] = spd
        stats = statsHash
        val ivsHash = HashMap<String, Int>()
        ivsHash["atk"] = atkiv
        ivsHash["def"] = defiv
        ivsHash["spatk"] = spatkiv
        ivsHash["spdef"] = spdefiv
        ivsHash["hp"] = hpiv
        ivsHash["spd"] = spdiv
        ivs = ivsHash


    }
}