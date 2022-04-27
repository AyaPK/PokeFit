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
import me.sargunvohra.lib.pokekotlin.model.Item

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
                    onFirstRun()
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

    fun onFirstRun() {
        val x = dataAccess.getTableSize("pokemonFromApi")
        val y = dataAccess.getTableSize("itemFromApi")

        if(dataAccess.getTableSize("pokemonFromApi") != 151) {
            dataAccess.clearTable("pokemonFromApi")
            allPokemonFromAPI
        }

        if (dataAccess.getTableSize("itemFromApi") != resources.getIntArray(R.array.itemIDs).size) {
            dataAccess.clearTable("itemFromApi")
            itemsFromAPI
        }
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

    private inner class RetrieveItemByID : AsyncTask<Int?, Void?, Item?>() {
        private val exception: Exception? = null
        override fun doInBackground(vararg p0: Int?): Item? {

            val pokeApi = PokeApiClient()

            val itemToReturn = p0[0]?.let { pokeApi.getItem(it) }
            System.out.println(itemToReturn?.name?.replace("-", " "))
            dataAccess.insertItemData(itemToReturn?.name, gson.toJson(itemToReturn))
            return itemToReturn
        }

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


    val itemsFromAPI: Unit
        get() {
            for (itemID in resources.getIntArray(R.array.itemIDs)) {
                RetrieveItemByID().execute(itemID)
            }
        }
}