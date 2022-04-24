package uk.co.ayaspace.pokefit.model.move

import com.google.gson.Gson

class MoveMetaData {
    // The minimum number of times this move hits. Null if it always only hits once.
    var minHits = 0
        private set

    // The maximum number of times this move hits. Null if it always only hits once.
    var maxHits = 0
        private set

    // The minimum number of turns this move continues to take effect. Null if it always only lasts one turn.
    var minTurns = 0
        private set

    // The maximum number of turns this move continues to take effect. Null if it always only lasts one turn.
    var maxTurns = 0
        private set

    // HP drain (if positive) or Recoil damage (if negative), in percent of damage done.
    var drain = 0
        private set

    // The amount of hp gained by the attacking Pokemon, in percent of it's maximum HP.
    var healing = 0
        private set

    // Critical hit rate bonus.
    var critRate = 0
        private set

    // The likelihood this attack will cause an ailment.
    var ailmentChance = 0
        private set

    // The likelihood this attack will cause the target Pokémon to flinch.
    var flinchChance = 0
        private set

    // The likelihood this attack will cause a stat change in the target Pokémon.
    var statChance = 0
        private set

    fun setMinHits(min_hits: Int): MoveMetaData {
        minHits = min_hits
        return this
    }

    fun setMaxHits(max_hits: Int): MoveMetaData {
        maxHits = max_hits
        return this
    }

    fun setMinTurns(min_turns: Int): MoveMetaData {
        minTurns = min_turns
        return this
    }

    fun setMaxTurns(max_turns: Int): MoveMetaData {
        maxTurns = max_turns
        return this
    }

    fun setDrain(drain: Int): MoveMetaData {
        this.drain = drain
        return this
    }

    fun setHealing(healing: Int): MoveMetaData {
        this.healing = healing
        return this
    }

    fun setCritRate(crit_rate: Int): MoveMetaData {
        critRate = crit_rate
        return this
    }

    fun setAilmentChance(ailment_chance: Int): MoveMetaData {
        ailmentChance = ailment_chance
        return this
    }

    fun setFlinchChance(flinch_chance: Int): MoveMetaData {
        flinchChance = flinch_chance
        return this
    }

    fun setStatChance(stat_chance: Int): MoveMetaData {
        statChance = stat_chance
        return this
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}