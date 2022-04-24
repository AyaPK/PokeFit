package uk.co.ayaspace.pokefit.ui.playerSettings

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.databinding.FragmentPlayerSettingsBinding
import uk.co.ayaspace.pokefit.utils.Database
import java.util.*

class PlayerSettingsFragment : Fragment() {
    private var binding: FragmentPlayerSettingsBinding? = null
    private var dataAccess: Database? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlayerSettingsBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val context = this.requireActivity().applicationContext
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        dataAccess = Database(context)
        val spinnerAgeBand = binding!!.ageBandSpinner
        val adapterAgeBand = ArrayAdapter.createFromResource(context,
                R.array.age_band, android.R.layout.simple_spinner_item)
        adapterAgeBand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAgeBand.adapter = adapterAgeBand
        val spinnerCurrentActivity = binding!!.currentSpinner
        val adapterCurrent = ArrayAdapter.createFromResource(context,
                R.array.current_activity_level, android.R.layout.simple_spinner_item)
        adapterCurrent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrentActivity.adapter = adapterCurrent
        val spinnerDesiredActivity = binding!!.desiredSpinner
        val adapterDesired = ArrayAdapter.createFromResource(context,
                R.array.desired_activity_level, android.R.layout.simple_spinner_item)
        adapterDesired.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDesiredActivity.adapter = adapterDesired
        val nameText: TextView = binding!!.enterNameInputField
        nameText.text = sharedPreferences.getString("playerName", "")
        val heightText: TextView = binding!!.enterHeightInputField
        heightText.text = sharedPreferences.getString("playerHeight", "")
        val weightText: TextView = binding!!.enterWeightInputField
        weightText.text = sharedPreferences.getString("playerWeight", "")
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}