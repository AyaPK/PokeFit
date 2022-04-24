package uk.co.ayaspace.pokefit.ui.daycare

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import uk.co.ayaspace.pokefit.databinding.FragmentDaycareEmptyBinding
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import uk.co.ayaspace.pokefit.model.pokemon.Pokemon
import uk.co.ayaspace.pokefit.utils.Database
import uk.co.ayaspace.pokefit.utils.StringVariableParser
import com.google.gson.Gson
import java.util.*

class DaycareFragment : Fragment(), SensorEventListener {
    private var binding: FragmentDaycareEmptyBinding? = null
    lateinit var dataAccess: Database
    private val gson = Gson()
    lateinit var thisContext: Context
    lateinit var partyArray: Array<Any>
    private val selected1: Pokemon? = null
    private var sensorManager: SensorManager? = null
    private val running = false
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDaycareEmptyBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        thisContext = requireActivity().applicationContext
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(thisContext)
        dataAccess = Database(thisContext)
        sensorManager = thisContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        if (stepSensor == null) {
            println("no sensor found")
            binding!!.stepButton.visibility = View.VISIBLE
        } else {
            binding!!.stepButton.visibility = View.GONE
            sensorManager!!.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
        dataAccess.retrievePartyDetails(thisContext)
        val res = this.resources

        val x = dataAccess.allDaycareData

        /* Words cannot express how angry I am that I will be committing
           the code with this big ugly if statement
           I hate this I hate this I hate this I hate this
           (This decides whether to show the daycare being full or empty
           based on whether the database has anything in it) */
        if (!dataAccess.allDaycareData.contains("null")) {
            showFullScreen()
            val slot1 = dataAccess.getPokemonObjectFromDaycare("1")
            val slot2 = dataAccess.getPokemonObjectFromDaycare("2")
            val imageView1 = binding!!.pokemon1ImageViewFull
            val resID1 = res.getIdentifier(StringVariableParser().ParseString(slot1.name, thisContext), "drawable", requireActivity().packageName)
            imageView1.setImageDrawable(AppCompatResources.getDrawable(thisContext, resID1))
            val imageView2 = binding!!.pokemon2ImageViewFull
            val resID2 = res.getIdentifier(StringVariableParser().ParseString(slot2.name, thisContext), "drawable", requireActivity().packageName)
            imageView2.setImageDrawable(AppCompatResources.getDrawable(thisContext, resID2))
            val steps = sharedPreferences.getInt("daycareStepsLeft", 0)
            val stepsToHatch = binding!!.stepsRemainingTextView
            stepsToHatch.text = "There are $steps steps left until an egg is ready!"
            if (sharedPreferences.getInt("daycareStepsLeft", 1) < 1) {
                binding!!.stepsRemainingTextView.visibility = View.GONE
                binding!!.collectEggFromDaycareButton.visibility = View.VISIBLE
            }
        } else {
            showEmptyScreen()
            val partyList = ArrayList<String>()
            for (i in 1..6) {
                val pokeString = sharedPreferences.getString("partySlot$i", null)
                if (pokeString != "null") {
                    if (pokeString!!.contains("eggdefault")) {
                        partyList.add("$i) egg")
                    } else {
                        val pokemon = gson.fromJson(pokeString, OwnedPokemon::class.java)
                        partyList.add(i.toString() + ") " + pokemon.name)
                    }
                } else {
                    partyList.add("-----")
                }
            }
            partyArray = partyList.toTypedArray()
            val spinner1 = binding!!.pokemon1Spinner
            val adapter = ArrayAdapter(thisContext, android.R.layout.simple_spinner_item, partyArray)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter
            val spinner2 = binding!!.pokemon2Spinner
            val adapter2 = ArrayAdapter(thisContext, android.R.layout.simple_spinner_item, partyArray)
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = adapter2
            spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    val selected = parentView.getItemAtPosition(position).toString()
                    val context = parentView.context
                    val text: CharSequence = selected
                    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                    val editor = sharedPreferences.edit()
                    val pokemon = sharedPreferences.getString("partySlot" + (position + 1), "null")
                    editor.putString("daycareSpinner1", pokemon)
                    editor.apply()
                    checkForNoEggs()
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                    //pass
                }
            }
            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    val selected = parentView.getItemAtPosition(position).toString()
                    val context = parentView.context
                    val text: CharSequence = selected
                    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                    val editor = sharedPreferences.edit()
                    if (text != "ditto") {
                        val pokemon = sharedPreferences.getString("partySlot" + (position + 1), "null")
                        editor.putString("daycareSpinner2", pokemon)
                    } else {
                        val baby = dataAccess.getPokemonObjectByName("ditto")
                        val babyAbility = baby.abilities[0]
                        val ditto = OwnedPokemon(baby.name,
                            babyAbility.ability.name,
                                baby.stats[1].baseStat, baby.stats[2].baseStat, baby.stats[3].baseStat,
                                baby.stats[4].baseStat, baby.stats[0].baseStat, baby.stats[5].baseStat,
                                0, 0, 0, 0, 0, 0, baby.id, baby.types[0].toString(), baby.types[0].toString(),
                                "ditto")
                        val dittoString = gson.toJson(ditto)
                        editor.putString("daycareSpinner2", dittoString)
                    }
                    editor.apply()
                    checkForNoEggs()
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {
                    //pass
                }
            }
        }
        return root
    }

    fun checkForNoEggs() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(thisContext)
        val slot1 = sharedPreferences.getString("daycareSpinner1", null)
        val slot2 = sharedPreferences.getString("daycareSpinner2", null)
        try {
            binding!!.breedButton.isClickable = !slot1!!.contains("stepsLeftToHatch") && !slot2!!.contains("stepsLeftToHatch") && slot1 != slot2 && slot1 != "-----" && slot2 != "-----"
        } catch (e: Exception) {
            binding!!.breedButton.isClickable = false
        }
    }

    fun showEmptyScreen() {
        binding!!.pokemon1TextView.visibility = View.VISIBLE
        binding!!.pokemon2TextView.visibility = View.VISIBLE
        binding!!.pokemon1Spinner.visibility = View.VISIBLE
        binding!!.pokemon2Spinner.visibility = View.VISIBLE
        binding!!.dittoButtonTextView.visibility = View.VISIBLE
        binding!!.dittoButton.visibility = View.VISIBLE
        binding!!.pokemon1ImageView.visibility = View.VISIBLE
        binding!!.pokemon2ImageView.visibility = View.VISIBLE
        binding!!.breedButton.visibility = View.VISIBLE
        binding!!.pokemon1ImageViewFull.visibility = View.GONE
        binding!!.pokemon2ImageViewFull.visibility = View.GONE
        binding!!.pokemonRelationTextView.visibility = View.GONE
        binding!!.stepsRemainingTextView.visibility = View.GONE
        binding!!.collectEggFromDaycareButton.visibility = View.GONE
        binding!!.stepButton.visibility = View.GONE
    }

    fun showFullScreen() {
        binding!!.pokemon1TextView.visibility = View.GONE
        binding!!.pokemon2TextView.visibility = View.GONE
        binding!!.pokemon1Spinner.visibility = View.GONE
        binding!!.pokemon2Spinner.visibility = View.GONE
        binding!!.dittoButtonTextView.visibility = View.GONE
        binding!!.dittoButton.visibility = View.GONE
        binding!!.pokemon1ImageView.visibility = View.GONE
        binding!!.pokemon2ImageView.visibility = View.GONE
        binding!!.breedButton.visibility = View.GONE
        binding!!.pokemon1ImageViewFull.visibility = View.VISIBLE
        binding!!.pokemon2ImageViewFull.visibility = View.VISIBLE
        binding!!.pokemonRelationTextView.visibility = View.VISIBLE
        binding!!.stepsRemainingTextView.visibility = View.VISIBLE
        binding!!.collectEggFromDaycareButton.visibility = View.GONE
        binding!!.stepButton.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onSensorChanged(event: SensorEvent) {
        try {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(thisContext)
            val stepsLeft = binding!!.stepsRemainingTextView
            val sensor = event.sensor
            val values = event.values
            var value = -1
            if (values.size > 0) {
                value = values[0].toInt()
            }
            if (sensor.type == Sensor.TYPE_STEP_DETECTOR) {
                var steps = sharedPreferences.getInt("daycareStepsLeft", 10)
                steps = steps - 1
                val editor = sharedPreferences.edit()
                editor.putInt("daycareStepsLeft", steps)
                editor.apply()
                if (steps > 0) {
                    val stepsToHatch = binding!!.stepsRemainingTextView
                    stepsToHatch.text = "There are $steps steps left until an egg is ready!"
                } else {
                    binding!!.stepsRemainingTextView.visibility = View.GONE
                    binding!!.collectEggFromDaycareButton.visibility = View.VISIBLE
                }
            }
        } catch (e: Exception) {
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
}