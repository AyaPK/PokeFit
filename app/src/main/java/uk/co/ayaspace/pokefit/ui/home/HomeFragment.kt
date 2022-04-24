package uk.co.ayaspace.pokefit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uk.co.ayaspace.pokefit.databinding.FragmentHomeBinding
import uk.co.ayaspace.pokefit.ui.home.HomeViewModel
import uk.co.ayaspace.pokefit.utils.Database
import uk.co.ayaspace.pokefit.utils.StringVariableParser

class HomeFragment : Fragment() {
    private var homeViewModel: HomeViewModel? = null
    private var binding: FragmentHomeBinding? = null
    private var dataAccess: Database? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val context = this.requireActivity().applicationContext
        dataAccess = Database(context)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        val context = this.requireActivity().applicationContext
        dataAccess = Database(context)
        val textView = binding!!.textDome
        val currencyText = binding!!.yourMoney
        val res = this.resources
        textView.text = StringVariableParser().ParseString("pn\$v's home screen", context)
        currencyText.text = "You currently have ₽" + dataAccess!!.currency
    }
}