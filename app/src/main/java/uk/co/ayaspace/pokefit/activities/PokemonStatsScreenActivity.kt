package uk.co.ayaspace.pokefit.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import uk.co.ayaspace.pokefit.utils.Database
import uk.co.ayaspace.pokefit.utils.StringVariableParser
import com.google.gson.Gson

class PokemonStatsScreenActivity : AppCompatActivity() {
    var gson = Gson()
    var dataAccess = Database(this)
    var pokemonString: String? = null
    var slot = 0
    var value = 0
    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_stats_screen)
        val data = intent
        pokemonString = data.getStringExtra("pokemon")
        slot = data.getIntExtra("slot", 0)
        val ownedPokemon = gson.fromJson(pokemonString, OwnedPokemon::class.java)
        value = calculatePokemonValue()
        val pokemonImage = findViewById<ImageView>(R.id.pokemonImage)
        val res = this.resources
        val resID = res.getIdentifier(StringVariableParser().ParseString(ownedPokemon.name, this@PokemonStatsScreenActivity), "drawable", this.packageName)
        pokemonImage.setImageDrawable(AppCompatResources.getDrawable(this@PokemonStatsScreenActivity, resID))
        val pokemonNameLabel = findViewById<TextView>(R.id.pokemonNameLabel)
        pokemonNameLabel.text = ownedPokemon.name
        val hpiv = findViewById<TextView>(R.id.HPIVStat)
        hpiv.text = "" + ownedPokemon.ivs["hp"]
        val atkiv = findViewById<TextView>(R.id.attackIVStat)
        atkiv.text = "" + ownedPokemon.ivs["atk"]
        val defiv = findViewById<TextView>(R.id.defenseIVStat)
        defiv.text = "" + ownedPokemon.ivs["def"]
        val spatkiv = findViewById<TextView>(R.id.specialAttackIVStat)
        spatkiv.text = "" + ownedPokemon.ivs["spatk"]
        val spdefiv = findViewById<TextView>(R.id.specialDefenseIVStat)
        spdefiv.text = "" + ownedPokemon.ivs["spdef"]
        val spdiv = findViewById<TextView>(R.id.speedIVStat)
        spdiv.text = "" + ownedPokemon.ivs["spd"]
        val valueStat = findViewById<TextView>(R.id.valueStat)
        valueStat.text = "₽$value"
        var eggGroup = ""

        val x = dataAccess.getSpeciesByName(ownedPokemon.name)
        System.out.println(x)
        System.out.println(x)




        for (e in dataAccess.getSpeciesByName(ownedPokemon.name).eggGroups) {
            System.out.println(e)
            eggGroup = eggGroup + e.name + " "
        }
        val eggGroupText = findViewById<TextView>(R.id.eggGroupStat)
        eggGroupText.text = eggGroup
    }

    fun onBackButtonPressed(view: View?) {
        finish()
    }

    fun onSellButtonPressed(view: View?) {
        dataAccess.updatePartySlot("" + slot, null)
        dataAccess.retrievePartyDetails(this@PokemonStatsScreenActivity)
        var oldCurrency = dataAccess.currency
        oldCurrency = oldCurrency + value
        dataAccess.updateCurrency(oldCurrency)
        val resultInt = Intent()
        resultInt.putExtra("sold", true)
        setResult(RESULT_OK, resultInt)
        finish()
    }

    fun calculatePokemonValue(): Int {
        val ownedPokemon = gson.fromJson(pokemonString, OwnedPokemon::class.java)
        val atk = ownedPokemon.ivs["atk"]!!
        val def = ownedPokemon.ivs["def"]!!
        val hp = ownedPokemon.ivs["hp"]!!
        val spd = ownedPokemon.ivs["spd"]!!
        val spatk = ownedPokemon.ivs["spatk"]!!
        val spdef = ownedPokemon.ivs["spdef"]!!

        var mult: Int = ownedPokemon.pokeid / 4
        if (mult < 1) { mult = 1 }

        return (6 + atk + def + hp + spd + spatk + spdef) * mult
    }
}