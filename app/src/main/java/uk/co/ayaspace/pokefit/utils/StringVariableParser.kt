package uk.co.ayaspace.pokefit.utils

import android.content.Context
import android.preference.PreferenceManager

class StringVariableParser {
    fun ParseString(stringToParse: String?, context: Context?): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val playerName = sharedPreferences.getString("playerName", "")
        val playerCurrentFitness = sharedPreferences.getString("playerCurrentFitness", "")
        val playerDesiredFitness = sharedPreferences.getString("playerDesiredFitness", "")
        val chosenStarter = sharedPreferences.getString("chosenStarter", "")
        val partySlot1 = sharedPreferences.getString("partySlot1", "null")
        val partySlot2 = sharedPreferences.getString("partySlot2", "null")
        val partySlot3 = sharedPreferences.getString("partySlot3", "null")
        val partySlot4 = sharedPreferences.getString("partySlot4", "null")
        val partySlot5 = sharedPreferences.getString("partySlot5", "null")
        val partySlot6 = sharedPreferences.getString("partySlot6", "null")
        return stringToParse
            ?.replace("pn\$v", playerName!!)
            ?.replace("cf\$v", playerCurrentFitness!!)
            ?.replace("df\$v", playerDesiredFitness!!)
            ?.replace("cs\$v", chosenStarter!!)
            ?.replace("ps1\$v", partySlot1!!)
            ?.replace("ps2\$v", partySlot2!!)
            ?.replace("ps3\$v", partySlot3!!)
            ?.replace("ps4\$v", partySlot4!!)
            ?.replace("ps5\$v", partySlot5!!)
            ?.replace("ps6\$v", partySlot6!!) ?: ""
    }
}