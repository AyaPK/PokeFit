package uk.co.ayaspace.pokefit.model.pokemon

import uk.co.ayaspace.pokefit.model.evolution.EvolutionChain
import uk.co.ayaspace.pokefit.model.resource.NamedAPIResourceList
import uk.co.ayaspace.pokefit.model.utility.Description
import uk.co.ayaspace.pokefit.model.utility.Name
import uk.co.ayaspace.pokefit.model.utility.NamedAPIResource
import uk.co.ayaspace.pokefit.utils.Information
import com.google.gson.Gson

class PokemonSpecies : NamedAPIResource() {
    // The identifier for this resource.
    var id = 0
        private set

    // The order in which species should be sorted. Based on National Dex order, except families are grouped together and sorted by stage.
    var order = 0
        private set

    // The chance of this Pokémon being female, in eighths; or -1 for genderless.
    var genderRate = 0
        private set

    // The base capture rate; up to 255. The higher the number, the easier the catch.
    var captureRate = 0
        private set

    // The happiness when caught by a normal Pokéball; up to 255. The higher the number, the happier the Pokémon.
    var baseHappiness = 0
        private set

    // Whether or not this is a baby Pokémon.
    var isBaby = false
        private set

    // Whether or not this is a legendary Pokémon.
    var isLegendary = false
        private set

    // Whether or not this is a mythical Pokémon.
    var isMythical = false
        private set

    // Initial hatch counter: one must walk 255 × (hatch_counter + 1) steps before this Pokémon's egg hatches, unless utilizing bonuses like Flame Body's.
    var hatchCounter = 0
        private set

    // Whether or not this Pokémon has visual gender differences.
    var hasGenderDifferences = false
        private set

    // Whether or not this Pokémon has multiple forms and can switch between them.
    var formsSwitchable = false
        private set

    // A list of Pokedexes and the indexes reserved within them for this Pokémon species.
    var pokedexNumbers: ArrayList<PokemonSpeciesDexEntry>? = null
        private set

    // A list of egg groups this Pokémon species is a member of.
    var eggGroups: ArrayList<EggGroup>? = null

    // The Pokémon species that evolves into this Pokemon_species.
    private var evolves_from_species: PokemonSpecies? = null

    // The evolution chain this Pokémon species is a member of.
    private var evolution_chain: EvolutionChain? = null

    // The name of this resource listed in different languages.
    var names: ArrayList<Name>? = null
        private set

    // Descriptions of different forms Pokémon take on within the Pokémon species.
    var formDescriptions: ArrayList<Description>? = null
        private set

    fun setId(id: Int): PokemonSpecies {
        this.id = id
        return this
    }

    fun setOrder(order: Int): PokemonSpecies {
        this.order = order
        return this
    }

    fun setGenderRate(gender_rate: Int): PokemonSpecies {
        genderRate = gender_rate
        return this
    }

    fun setCaptureRate(capture_rate: Int): PokemonSpecies {
        captureRate = capture_rate
        return this
    }

    fun setBaseHappiness(base_happiness: Int): PokemonSpecies {
        baseHappiness = base_happiness
        return this
    }

    fun setIsBaby(is_baby: Boolean): PokemonSpecies {
        isBaby = is_baby
        return this
    }

    fun setIsLegendary(is_legendary: Boolean): PokemonSpecies {
        isLegendary = is_legendary
        return this
    }

    fun setIsMythical(is_mythical: Boolean): PokemonSpecies {
        isMythical = is_mythical
        return this
    }

    fun setHatchCounter(hatch_counter: Int): PokemonSpecies {
        hatchCounter = hatch_counter
        return this
    }

    fun setHasGenderDifferences(has_gender_differences: Boolean): PokemonSpecies {
        hasGenderDifferences = has_gender_differences
        return this
    }

    fun setFormsSwitchable(forms_switchable: Boolean): PokemonSpecies {
        formsSwitchable = forms_switchable
        return this
    }

    fun setPokedexNumbers(pokedex_numbers: ArrayList<PokemonSpeciesDexEntry>?): PokemonSpecies {
        pokedexNumbers = pokedex_numbers
        return this
    }

    val evolvesFromSpecies: PokemonSpecies?
        get() {
            if (!evolves_from_species!!.isFetched) {
                evolves_from_species = evolves_from_species!!.get()
            }
            return evolves_from_species
        }

    fun setEvolvesFromSpecies(evolves_from_species: PokemonSpecies?): PokemonSpecies {
        this.evolves_from_species = evolves_from_species
        return this
    }

    val evolutionChain: EvolutionChain?
        get() {
            if (!evolution_chain!!.isFetched) {
                evolution_chain = evolution_chain!!.get()
            }
            return evolution_chain
        }

    fun setEvolutionChain(evolution_chain: EvolutionChain?): PokemonSpecies {
        this.evolution_chain = evolution_chain
        return this
    }

    fun setNames(names: ArrayList<Name>?): PokemonSpecies {
        this.names = names
        return this
    }

    fun setFormDescriptions(form_descriptions: ArrayList<Description>?): PokemonSpecies {
        formDescriptions = form_descriptions
        return this
    }

    fun get(): PokemonSpecies {
        return Companion[url]
    }

    companion object {
        private operator fun get(url: String?): PokemonSpecies {
            val obj: PokemonSpecies = Gson().fromJson(Information().fromInternet(url), PokemonSpecies::class.java)
            obj.isFetched = true
            return obj
        }

        fun getList(limit: Int, offset: Int): NamedAPIResourceList {
            return NamedAPIResourceList.getList("pokemon-species", limit, offset)
        }

        fun getById(id: Int): PokemonSpecies {
            return Companion["https://pokeapi.co/api/v2/pokemon-species/$id"]
        }

        fun getByName(name: String): PokemonSpecies {
            return Companion["https://pokeapi.co/api/v2/pokemon-species/$name"]
        }
    }
}