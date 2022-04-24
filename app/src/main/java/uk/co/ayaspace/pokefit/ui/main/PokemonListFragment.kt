package uk.co.ayaspace.pokefit.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.pokefit.MyItemRecyclerViewAdapter
import uk.co.ayaspace.pokefit.R
import uk.co.ayaspace.pokefit.utils.Database
import java.util.*

/**
 * A fragment representing a list of Items.
 */
class PokemonListFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment() {
    // TODO: Customize parameters
    private var mColumnCount = 1
    var dataAccess: Database? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mColumnCount = requireArguments().getInt(ARG_COLUMN_COUNT)
        }
        val context = this.requireActivity().applicationContext
        dataAccess = Database(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            view.adapter = MyItemRecyclerViewAdapter(dataAccess!!.allPokemonDataAsList)
        }
        return view
    }

    companion object {
        // TODO: Customize parameter argument names
        private const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): PokemonListFragment {
            val fragment = PokemonListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}