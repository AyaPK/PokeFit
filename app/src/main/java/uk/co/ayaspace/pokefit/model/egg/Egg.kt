package uk.co.ayaspace.pokefit.model.egg

import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import uk.co.ayaspace.pokefit.model.utility.PartySlot

class Egg(var stepsToHatch: Int, pokemon: OwnedPokemon) : PartySlot("eggdefault") {
    var stepsLeftToHatch: Int
    private var pokemon: OwnedPokemon
    fun getPokemon(): OwnedPokemon {
        return pokemon
    }

    fun setPokemon(pokemon: OwnedPokemon) {
        this.pokemon = pokemon
    }

    fun hatchingDescription(): String {
        return if (stepsLeftToHatch < stepsToHatch * 0.3) {
            "Sounds can be heard coming from inside! It will hatch soon!"
        } else if (stepsLeftToHatch < stepsToHatch * 0.6) {
            "It appears to move occasionally. It may be close to hatching."
        } else {
            "What will hatch from this? It doesn't seem close to hatching."
        }
    }

    init {
        stepsLeftToHatch = stepsToHatch
        this.pokemon = pokemon
    }
}