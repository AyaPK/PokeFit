package uk.co.ayaspace.pokefit.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.activities.introductionActivities.FirstTimePlayerWelcomeScreenActivity
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import uk.co.ayaspace.pokefit.utils.Database
import com.google.gson.Gson
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient

class MainActivity : AppCompatActivity() {
    var dataAccess = Database(this)
    var gson = Gson()
    var pokemonFound = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)

        val welcomeThread: Thread = object : Thread() {
            override fun run() {
                try {
                    super.run()
                    if (!dataAccess.checkTableExists("pokemonFromApi")) {
                        onFirstRun()
                    } //Pull pokemon list from API if it doesn't exist in local storage
                } catch (e: Exception) {
                    println(e)
                } finally {
                    val intent = Intent(this@MainActivity, FirstTimePlayerWelcomeScreenActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        welcomeThread.start()
        setContentView(R.layout.activity_main)
    }


    private inner class RetrievePokemonByID : AsyncTask<Int?, Void?, Pokemon>() {
        private val exception: Exception? = null
        override fun doInBackground(vararg p0: Int?): Pokemon? {

            val pokeApi = PokeApiClient()

            val pokemonToReturn = p0[0]?.let { pokeApi.getPokemon(it) }
            val speciesToAdd = pokeApi.getPokemonSpecies(pokemonToReturn?.species!!.id)
            dataAccess.insertPokemonData(pokemonToReturn.name, gson.toJson(pokemonToReturn), gson.toJson(speciesToAdd))
            pokemonFound++
            return pokemonToReturn
        }

    }

    fun onFirstRun() {
        allPokemonFromAPI
    }

    val allPokemonFromAPI: Unit
        get() {
            for (x in 1..151) {
                RetrievePokemonByID().execute(x)
            }
            while (true) {
                if (pokemonFound >= 151) {
                    break
                }
            }
        }
}