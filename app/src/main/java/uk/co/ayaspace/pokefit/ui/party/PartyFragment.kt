package uk.co.ayaspace.pokefit.ui.party

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uk.co.ayaspace.pokefit.databinding.FragmentPartyBinding
import uk.co.ayaspace.pokefit.model.egg.Egg
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import uk.co.ayaspace.pokefit.model.utility.PartySlot
import uk.co.ayaspace.pokefit.utils.Database
import uk.co.ayaspace.pokefit.utils.StringVariableParser
import com.google.gson.Gson

class PartyFragment : Fragment() {
    private var binding: FragmentPartyBinding? = null
    private var dataAccess: Database? = null
    var gson = Gson()
    override fun onResume() {
        super.onResume()
        val context = this.requireActivity().applicationContext
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var slot1: PartySlot? = null
        var slot2: PartySlot? = null
        var slot3: PartySlot? = null
        var slot4: PartySlot? = null
        var slot5: PartySlot? = null
        var slot6: PartySlot? = null
        dataAccess!!.retrievePartyDetails(context)
        val slot1string = sharedPreferences.getString("partySlot1", null)
        val slot2string = sharedPreferences.getString("partySlot2", null)
        val slot3string = sharedPreferences.getString("partySlot3", null)
        val slot4string = sharedPreferences.getString("partySlot4", null)
        val slot5string = sharedPreferences.getString("partySlot5", null)
        val slot6string = sharedPreferences.getString("partySlot6", null)
        try {
            slot1 = if (!slot1string!!.contains("stepsLeftToHatch")) {
                gson.fromJson(slot1string, OwnedPokemon::class.java)
            } else {
                gson.fromJson(slot1string, Egg::class.java)
            }
        } catch (e: Exception) {
        }
        try {
            slot2 = if (!slot2string!!.contains("stepsLeftToHatch")) {
                gson.fromJson(slot2string, OwnedPokemon::class.java)
            } else {
                gson.fromJson(slot2string, Egg::class.java)
            }
        } catch (e: Exception) {
        }
        try {
            slot3 = if (!slot3string!!.contains("stepsLeftToHatch")) {
                gson.fromJson(slot3string, OwnedPokemon::class.java)
            } else {
                gson.fromJson(slot3string, Egg::class.java)
            }
        } catch (e: Exception) {
        }
        try {
            slot4 = if (!slot4string!!.contains("stepsLeftToHatch")) {
                gson.fromJson(slot4string, OwnedPokemon::class.java)
            } else {
                gson.fromJson(slot4string, Egg::class.java)
            }
        } catch (e: Exception) {
        }
        try {
            slot5 = if (!slot5string!!.contains("stepsLeftToHatch")) {
                gson.fromJson(slot5string, OwnedPokemon::class.java)
            } else {
                gson.fromJson(slot5string, Egg::class.java)
            }
        } catch (e: Exception) {
        }
        try {
            slot6 = if (!slot6string!!.contains("stepsLeftToHatch")) {
                gson.fromJson(slot6string, OwnedPokemon::class.java)
            } else {
                gson.fromJson(slot6string, Egg::class.java)
            }
        } catch (e: Exception) {
        }
        val textView1 = binding!!.textParty1
        val textView2 = binding!!.textParty2
        val textView3 = binding!!.textParty3
        val textView4 = binding!!.textParty4
        val textView5 = binding!!.textParty5
        val textView6 = binding!!.textParty6
        val imageView1 = binding!!.pokemonImageParty1
        val imageView2 = binding!!.pokemonImageParty2
        val imageView3 = binding!!.pokemonImageParty3
        val imageView4 = binding!!.pokemonImageParty4
        val imageView5 = binding!!.pokemonImageParty5
        val imageView6 = binding!!.pokemonImageParty6
        val res = this.resources
        try {
            textView1.visibility = View.VISIBLE
            imageView1.visibility = View.VISIBLE
            val resID1 = res.getIdentifier(StringVariableParser().ParseString(slot1!!.name, context), "drawable", requireActivity().packageName)
            imageView1.setImageDrawable(AppCompatResources.getDrawable(context, resID1))
            val button = binding!!.partyButton1
            button.visibility = View.VISIBLE
            textView1.text = slot1.name
        } catch (e: Exception) {
            textView1.visibility = View.GONE
            imageView1.visibility = View.GONE
            binding!!.partyButton1.visibility = View.GONE
        }
        try {
            textView2.visibility = View.VISIBLE
            imageView2.visibility = View.VISIBLE
            val resID2 = res.getIdentifier(StringVariableParser().ParseString(slot2!!.name, context), "drawable", requireActivity().packageName)
            imageView2.setImageDrawable(AppCompatResources.getDrawable(context, resID2))
            val button = binding!!.partyButton2
            button.visibility = View.VISIBLE
            textView2.text = slot2.name
        } catch (e: Exception) {
            textView2.visibility = View.GONE
            imageView2.visibility = View.GONE
            binding!!.partyButton2.visibility = View.GONE
        }
        try {
            textView3.visibility = View.VISIBLE
            imageView3.visibility = View.VISIBLE
            val resID3 = res.getIdentifier(StringVariableParser().ParseString(slot3!!.name, context), "drawable", requireActivity().packageName)
            imageView3.setImageDrawable(AppCompatResources.getDrawable(context, resID3))
            val button = binding!!.partyButton3
            button.visibility = View.VISIBLE
            textView3.text = slot3.name
        } catch (e: Exception) {
            textView3.visibility = View.GONE
            imageView3.visibility = View.GONE
            binding!!.partyButton3.visibility = View.GONE
        }
        try {
            textView4.visibility = View.VISIBLE
            imageView4.visibility = View.VISIBLE
            val resID4 = res.getIdentifier(StringVariableParser().ParseString(slot4!!.name, context), "drawable", requireActivity().packageName)
            imageView4.setImageDrawable(AppCompatResources.getDrawable(context, resID4))
            val button = binding!!.partyButton4
            button.visibility = View.VISIBLE
            textView4.text = slot4.name
        } catch (e: Exception) {
            textView4.visibility = View.GONE
            imageView4.visibility = View.GONE
            binding!!.partyButton4.visibility = View.GONE
        }
        try {
            textView5.visibility = View.VISIBLE
            imageView5.visibility = View.VISIBLE
            val resID5 = res.getIdentifier(StringVariableParser().ParseString(slot5!!.name, context), "drawable", requireActivity().packageName)
            imageView5.setImageDrawable(AppCompatResources.getDrawable(context, resID5))
            val button = binding!!.partyButton5
            button.visibility = View.VISIBLE
            textView5.text = slot5.name
        } catch (e: Exception) {
            textView5.visibility = View.GONE
            imageView5.visibility = View.GONE
            binding!!.partyButton5.visibility = View.GONE
        }
        try {
            textView6.visibility = View.VISIBLE
            imageView6.visibility = View.VISIBLE
            val resID6 = res.getIdentifier(StringVariableParser().ParseString(slot6!!.name, context), "drawable", requireActivity().packageName)
            imageView6.setImageDrawable(AppCompatResources.getDrawable(context, resID6))
            val button = binding!!.partyButton6
            button.visibility = View.VISIBLE
            textView6.text = slot6.name
        } catch (e: Exception) {
            textView6.visibility = View.GONE
            imageView6.visibility = View.GONE
            binding!!.partyButton6.visibility = View.GONE
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPartyBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val context = this.requireActivity().applicationContext
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        dataAccess = Database(context)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}