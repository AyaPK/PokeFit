package uk.co.ayaspace.pokefit.model.pokemon

import com.google.gson.Gson

class PokemonStat {
    // The stat the Pokémon has.
    private var stat: Stat? = null

    // The effort points (EV) the Pokémon has in the stat.
    var effort = 0
        private set

    // The base value of the stat.
    var baseStat = 0
        private set

    fun getStat(): Stat? {
        if (!stat!!.isFetched) {
            stat = stat!!.get()
        }
        return stat
    }

    fun setStat(stat: Stat?): PokemonStat {
        this.stat = stat
        return this
    }

    fun setEffort(effort: Int): PokemonStat {
        this.effort = effort
        return this
    }

    fun setBaseStat(base_stat: Int): PokemonStat {
        baseStat = base_stat
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}