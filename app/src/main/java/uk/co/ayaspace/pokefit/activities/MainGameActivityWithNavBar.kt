package uk.co.ayaspace.pokefit.activities

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.StorePageActivity
import uk.co.ayaspace.pokefit.databinding.ActivityMainGameWithNavBarBinding
import uk.co.ayaspace.pokefit.model.egg.Egg
import uk.co.ayaspace.pokefit.model.egg.EggFactory
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import uk.co.ayaspace.pokefit.utils.Database

class MainGameActivityWithNavBar : AppCompatActivity() {

    private lateinit var binding: ActivityMainGameWithNavBarBinding
    private val gson = Gson()
    private var dataAccess: Database? = null
    private val EGG_ACTIVITY = 1
    var navView: BottomNavigationView? = null
    var partyLaunchForResult: ActivityResultLauncher<Intent>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(Toolbar(this))
        dataAccess = Database(this)
        binding = ActivityMainGameWithNavBarBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        partyLaunchForResult = registerForActivityResult(
            StartActivityForResult()
        ) { result ->
            println(result.resultCode)
            if (result.resultCode == RESULT_OK) {
                val data = result.data!!
                val hatched = data.getBooleanExtra("hatched", false)
                val sold = data.getBooleanExtra("sold", false)
                if (hatched) {
                    //navView.setSelectedItemId(R.id.navigation_home);
                }
                if (sold) {
                    //navView.setSelectedItemId(R.id.navigation_home);
                }
            }
        }
        navView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main_nav) as NavHostFragment
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_party, R.id.navigation_daycare
        ).build()
        val navController = navHostFragment.navController
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(binding.navView, navController)
    }

    fun onPartyButtonClicked(view: View) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this@MainGameActivityWithNavBar)
        var pokemon: String? = null
        var slot = 0
        val id = view.id
        when (id) {
            R.id.party_button_1 -> {
                pokemon = sharedPreferences.getString("partySlot1", "")
                slot = 1
            }
            R.id.party_button_2 -> {
                pokemon = sharedPreferences.getString("partySlot2", "")
                slot = 2
            }
            R.id.party_button_3 -> {
                pokemon = sharedPreferences.getString("partySlot3", "")
                slot = 3
            }
            R.id.party_button_4 -> {
                pokemon = sharedPreferences.getString("partySlot4", "")
                slot = 4
            }
            R.id.party_button_5 -> {
                pokemon = sharedPreferences.getString("partySlot5", "")
                slot = 5
            }
            R.id.party_button_6 -> {
                pokemon = sharedPreferences.getString("partySlot6", "")
                slot = 6
            }
            else -> {
            }
        }
        if (!pokemon!!.contains("stepsLeftToHatch")) {
            val intent = Intent(this, PokemonStatsScreenActivity::class.java)
            intent.putExtra("pokemon", pokemon)
            intent.putExtra("slot", slot)
            partyLaunchForResult!!.launch(intent)
        } else {
            val intent = Intent(this, EggStatsScreenActivity::class.java)
            intent.putExtra("egg", pokemon)
            intent.putExtra("slot", slot)
            partyLaunchForResult!!.launch(intent)
        }
    }

    fun onDaycareToggleClicked(view: View) {
        val toggleButton = view as ToggleButton
        if (!toggleButton.isChecked) {
            val sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this@MainGameActivityWithNavBar)
            val partyList = ArrayList<String>()
            for (i in 1..6) {
                val pokeString = sharedPreferences.getString("partySlot$i", null)
                if (pokeString != "null") {
                    if (pokeString!!.contains("eggdefault")) {
                        partyList.add("$i) egg")
                    } else {
                        val pokemon: OwnedPokemon =
                            gson.fromJson(pokeString, OwnedPokemon::class.java)
                        partyList.add(i.toString() + ") " + pokemon.name)
                    }
                } else {
                    partyList.add("-----")
                }
            }
            val partyArray: Array<Any> = partyList.toTypedArray()
            val spinner = findViewById<Spinner>(R.id.pokemon2Spinner)
            val adapter = ArrayAdapter(
                this@MainGameActivityWithNavBar,
                android.R.layout.simple_spinner_item,
                partyArray
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        } else {
            val array = ArrayList<String>()
            array.add("ditto")
            val partyArray: Array<Any> = array.toTypedArray()
            val spinner = findViewById<Spinner>(R.id.pokemon2Spinner)
            val adapter = ArrayAdapter(
                this@MainGameActivityWithNavBar,
                android.R.layout.simple_spinner_item,
                partyArray
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun onDropOffButtonPressed(view: View?) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this@MainGameActivityWithNavBar)
        val pokemon1: OwnedPokemon = gson.fromJson(
            sharedPreferences.getString("daycareSpinner1", null),
            OwnedPokemon::class.java
        )
        val pokemon2: OwnedPokemon = gson.fromJson(
            sharedPreferences.getString("daycareSpinner2", null),
            OwnedPokemon::class.java
        )
        val eggToBeMade: Egg? = EggFactory().buildEggFromParents(pokemon1, pokemon2, dataAccess!!)
        if (eggToBeMade != null) {
            dataAccess!!.updateDaycareSlot("1", sharedPreferences.getString("daycareSpinner1", null))
            dataAccess!!.updateDaycareSlot("2", sharedPreferences.getString("daycareSpinner2", null))
            dataAccess!!.updateDaycareSlot("3", gson.toJson(eggToBeMade))
            System.out.println(dataAccess!!.allDaycareData)
            val editor = sharedPreferences.edit()
            editor.putInt("daycareStepsLeft", eggToBeMade.stepsLeftToHatch / 10)
            editor.apply()
            for (i in 1..6) {
                val slot: String = dataAccess!!.getPartySlot("" + i)
                if (slot == sharedPreferences.getString("daycareSpinner1", "NOTHING")) {
                    dataAccess!!.updatePartySlot("" + i, null)
                    break
                }
            }
            for (i in 1..6) {
                val slot: String = dataAccess!!.getPartySlot("" + i)
                if (slot == sharedPreferences.getString("daycareSpinner2", "NOTHING")) {
                    dataAccess!!.updatePartySlot("" + i, null)
                    break
                }
            }
            navView!!.selectedItemId = R.id.navigation_party
        } else {
            println("not breedable")
        }
    }

    fun onStep(view: View?) {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this@MainGameActivityWithNavBar)
        val stepsLeft = findViewById<TextView>(R.id.stepsRemainingTextView)
        var steps = sharedPreferences.getInt("daycareStepsLeft", 10)
        steps = steps - 1
        val editor = sharedPreferences.edit()
        editor.putInt("daycareStepsLeft", steps)
        editor.apply()
        val stepsToHatch = findViewById<TextView>(R.id.stepsRemainingTextView)
        stepsToHatch.text = "There are $steps steps left until an egg is ready!"
        if (steps > 0) {
            stepsToHatch.text = "There are $steps steps left until an egg is ready!"
        } else {
            stepsToHatch.visibility = View.GONE
            findViewById<View>(R.id.collectEggFromDaycareButton).visibility = View.VISIBLE
            findViewById<View>(R.id.stepButton).visibility = View.GONE
        }
    }

    fun removeEggFromDaycare(view: View?) {
        val eggToTake: Egg = dataAccess!!.eggObjectFromDaycare
        val slot1: OwnedPokemon = dataAccess!!.getPokemonObjectFromDaycare("1")
        val slot2: OwnedPokemon = dataAccess!!.getPokemonObjectFromDaycare("2")
        var firstEmptySlot = 1
        for (i in 1..7) {
            val slot: String = dataAccess!!.getPartySlot("" + i)
            if (slot == "null") {
                firstEmptySlot = i
                break
            }
        }
        dataAccess!!.updatePartySlot("" + firstEmptySlot, gson.toJson(slot1))
        if (!slot2.name.equals("ditto")) {
            firstEmptySlot = 1
            for (i in 1..7) {
                val slot: String = dataAccess!!.getPartySlot("" + i)
                if (slot == "null") {
                    firstEmptySlot = i
                    break
                }
            }
            dataAccess!!.updatePartySlot("" + firstEmptySlot, gson.toJson(slot2))
        }
        firstEmptySlot = 1
        for (i in 1..7) {
            val slot: String = dataAccess!!.getPartySlot("" + i)
            if (slot == "null") {
                firstEmptySlot = i
                break
            }
        }
        if (firstEmptySlot == 7) {
            println("party full")
            return
        }
        val eggString = gson.toJson(eggToTake)
        dataAccess!!.updatePartySlot("" + firstEmptySlot, eggString)
        dataAccess!!.updateDaycareSlot("1", null)
        dataAccess!!.updateDaycareSlot("2", null)
        dataAccess!!.updateDaycareSlot("3", null)
        findViewById<View>(R.id.collectEggFromDaycareButton).visibility = View.GONE
        navView!!.selectedItemId = R.id.navigation_party
    }

    fun onStoreButtonClicked(view: View?) {
        val i = Intent(this@MainGameActivityWithNavBar, StorePageActivity::class.java)
        startActivity(i)
    }
}