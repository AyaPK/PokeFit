package uk.co.ayaspace.pokefit.model.evolution

import uk.co.ayaspace.pokefit.model.pokemon.PokemonSpecies
import com.google.gson.Gson

class ChainLink {
    // Whether or not this link is for a baby Pokémon. This would only ever be true on the base link.
    var isBaby = false
        private set

    // The Pokémon species at this point in the evolution chain.
    private var species: PokemonSpecies? = null

    // All details regarding the specific details of the referenced Pokémon species evolution.
    var evolutionDetails: ArrayList<EvolutionDetail>? = null
        private set

    // A List of chain objects.
    var evolvesTo: ArrayList<ChainLink>? = null
        private set

    fun setIsBaby(is_baby: Boolean): ChainLink {
        isBaby = is_baby
        return this
    }

    fun getSpecies(): PokemonSpecies? {
        if (!species!!.isFetched) {
            species = species!!.get()
        }
        return species
    }

    fun setSpecies(species: PokemonSpecies?): ChainLink {
        this.species = species
        return this
    }

    fun setEvolutionDetails(evolution_details: ArrayList<EvolutionDetail>?): ChainLink {
        evolutionDetails = evolution_details
        return this
    }

    fun setEvolvesTo(evolves_to: ArrayList<ChainLink>?): ChainLink {
        evolvesTo = evolves_to
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}