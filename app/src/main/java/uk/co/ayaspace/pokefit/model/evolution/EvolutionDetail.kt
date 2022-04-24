package uk.co.ayaspace.pokefit.model.evolution

import uk.co.ayaspace.pokefit.model.item.Item
import uk.co.ayaspace.pokefit.model.move.Move
import uk.co.ayaspace.pokefit.model.pokemon.PokemonSpecies
import uk.co.ayaspace.pokefit.model.pokemon.Type
import com.google.gson.Gson

class EvolutionDetail {
    // The item required to cause evolution this into Pokémon species.
    private var item: Item? = null

    // The id of the gender of the evolving Pokémon species must be in order to evolve into this Pokémon species.
    var gender = 0
        private set

    // The item the evolving Pokémon species must be holding during the evolution trigger event to evolve into this Pokémon species.
    private var held_item: Item? = null

    // The move that must be known by the evolving Pokémon species during the evolution trigger event in order to evolve into this Pokémon species.
    private var known_move: Move? = null

    // The evolving Pokémon species must know a move with this type during the evolution trigger event in order to evolve into this Pokémon species.
    private var known_move_type: Type? = null

    // The minimum required level of the evolving Pokémon species to evolve into this Pokémon species.
    var minLevel = 0
        private set

    // The minimum required level of happiness the evolving Pokémon species to evolve into this Pokémon species.
    var minHappiness = 0
        private set

    // The minimum required level of beauty the evolving Pokémon species to evolve into this Pokémon species.
    var minBeauty = 0
        private set

    // The minimum required level of affection the evolving Pokémon species to evolve into this Pokémon species.
    var minAffection = 0
        private set

    // Whether or not it must be raining in the overworld to cause evolution this Pokémon species.
    var needsOverworldRain = false
        private set

    // The Pokémon species that must be in the players party in order for the evolving Pokémon species to evolve into this Pokémon species.
    private var party_species: PokemonSpecies? = null

    // The player must have a Pokémon of this type in their party during the evolution trigger event in order for the evolving Pokémon species to evolve into this Pokémon species.
    private var party_type: Type? = null

    // The required relation between the Pokémon's Attack and Defense stats. 1 means Attack > Defense. 0 means Attack = Defense. -1 means Attack < Defense.
    var relativePhysicalStats = 0
        private set

    // The required time of day. Day or night.
    var timeOfDay: String? = null
        private set

    // Pokémon species for which this one must be traded.
    private var trade_species: PokemonSpecies? = null

    // Whether or not the 3DS needs to be turned upside-down as this Pokémon levels up.
    var turnUpsideDown = false
        private set

    fun getItem(): Item? {
        return item
    }

    fun setItem(item: Item?): EvolutionDetail {
        this.item = item
        return this
    }

    fun setGender(gender: Int): EvolutionDetail {
        this.gender = gender
        return this
    }

    val heldItem: Item?
        get() {
            return held_item
        }

    fun setHeldItem(held_item: Item?): EvolutionDetail {
        this.held_item = held_item
        return this
    }

    val knownMove: Move?
        get() {
            return known_move
        }

    fun setKnownMove(known_move: Move?): EvolutionDetail {
        this.known_move = known_move
        return this
    }

    val knownMoveType: Type?
        get() {
            return known_move_type
        }

    fun setKnownMoveType(known_move_type: Type?): EvolutionDetail {
        this.known_move_type = known_move_type
        return this
    }

    fun setMinLevel(min_level: Int): EvolutionDetail {
        minLevel = min_level
        return this
    }

    fun setMinHappiness(min_happiness: Int): EvolutionDetail {
        minHappiness = min_happiness
        return this
    }

    fun setMinBeauty(min_beauty: Int): EvolutionDetail {
        minBeauty = min_beauty
        return this
    }

    fun setMinAffection(min_affection: Int): EvolutionDetail {
        minAffection = min_affection
        return this
    }

    fun setNeedsOverworldRain(needs_overworld_rain: Boolean): EvolutionDetail {
        needsOverworldRain = needs_overworld_rain
        return this
    }

    val partySpecies: PokemonSpecies?
        get() {
            return party_species
        }

    fun setPartySpecies(party_species: PokemonSpecies?): EvolutionDetail {
        this.party_species = party_species
        return this
    }

    val partyType: Type?
        get() {
            return party_type
        }

    fun setPartyType(party_type: Type?): EvolutionDetail {
        this.party_type = party_type
        return this
    }

    fun setRelativePhysicalStats(relative_physical_stats: Int): EvolutionDetail {
        relativePhysicalStats = relative_physical_stats
        return this
    }

    fun setTimeOfDay(time_of_day: String?): EvolutionDetail {
        timeOfDay = time_of_day
        return this
    }

    val tradeSpecies: PokemonSpecies?
        get() {
            return trade_species
        }

    fun setTradeSpecies(trade_species: PokemonSpecies?): EvolutionDetail {
        this.trade_species = trade_species
        return this
    }

    fun setTurnUpsideDown(turn_upside_down: Boolean): EvolutionDetail {
        turnUpsideDown = turn_upside_down
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}