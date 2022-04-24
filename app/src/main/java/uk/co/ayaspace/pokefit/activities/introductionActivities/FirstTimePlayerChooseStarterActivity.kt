package uk.co.ayaspace.pokefit.activities.introductionActivities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.activities.MainGameActivityWithNavBar
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import me.sargunvohra.lib.pokekotlin.model.Ability
import uk.co.ayaspace.pokefit.utils.Database
import uk.co.ayaspace.pokefit.utils.Typewriter
import com.google.gson.Gson
import me.sargunvohra.lib.pokekotlin.model.PokemonAbility
import java.lang.reflect.InvocationTargetException

class FirstTimePlayerChooseStarterActivity : AppCompatActivity() {
    var dataAccess = Database(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_time_player_choose_starter)
        displayText("Let's choose your starter!")
    }

    fun onStarterButtonPressed(view: View) {
        val id = view.id
        when (id) {
            R.id.oddishImageButton -> confirmStarterChoice("oddish")
            R.id.poliwagImageButton -> confirmStarterChoice("poliwag")
            R.id.gastlyImageButton -> confirmStarterChoice("gastly")
            else -> {
            }
        }
    }

    fun confirmStarterChoice(starter: String) {
        val inflater = LayoutInflater.from(this)
        val promptsView = inflater.inflate(R.layout.confirmation_prompt_dialog, null)
        val promptText = promptsView.findViewById<TextView>(R.id.editTextDialogUserPrompt)
        promptText.text = "is $starter okay?"
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(promptsView)
        try {
            val userInput = promptsView.findViewById<View>(R.id.editTextDialogUserInput) as TextView
        }
        catch ( e:InvocationTargetException) {
            // Answer:
            e.cause?.printStackTrace();
        } catch (e: Exception) {
            // generic exception handling
            e.printStackTrace();
        }
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Submit"
                ) { dialog, id ->
                    val preferences = PreferenceManager.getDefaultSharedPreferences(this@FirstTimePlayerChooseStarterActivity)
                    val editor = preferences.edit()
                    editor.putString("chosenStarter", starter)
                    val gson = Gson()

                    //Build starter pokémon as object and update Sysprefs and Database with this information
                    val pkmn = dataAccess.getPokemonObjectByName(starter)
                    val babyAbility = pkmn.abilities[0]
                    val starterPokemon =
                        babyAbility?.ability?.name.let {
                            pkmn.stats?.get(1)?.baseStat?.let { it1 ->
                                OwnedPokemon(pkmn.name, it,
                                    it1, pkmn.stats!![2].baseStat, pkmn.stats!![3].baseStat,
                                    pkmn.stats!![4].baseStat, pkmn.stats!![0].baseStat, pkmn.stats!![5].baseStat,
                                    0, 0, 0, 0, 0, 0, pkmn.id, pkmn.types?.get(0).toString(), pkmn.types?.get(0)
                                        .toString(),
                                    starter)
                            }
                        }
                    editor.putString("partySlot1", gson.toJson(starterPokemon))
                    editor.putBoolean("playerExists", true)
                    editor.apply()
                    dataAccess.updatePartyDetails(this@FirstTimePlayerChooseStarterActivity)
                    val i = Intent(this@FirstTimePlayerChooseStarterActivity, MainGameActivityWithNavBar::class.java)
                    startActivity(i)
                    finish()
                }
                .setNegativeButton("cancel"
                ) { dialog, i ->
                    //pass
                }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun displayText(stringToDisplay: String) {
        val animatedText = findViewById<View>(R.id.typewriter) as Typewriter
        animatedText.setCharacterDelay(30)
        animatedText.animateText(stringToDisplay)
    }
}