package uk.co.ayaspace.pokefit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.ayaspace.pokefit.databinding.FragmentPokemonListBinding
import uk.co.ayaspace.pokefit.model.pokemon.OwnedPokemon
import uk.co.ayaspace.pokefit.utils.Database
import com.google.gson.Gson

class MyItemRecyclerViewAdapter(private val mValues: List<String>) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mContentView.text = mValues[position]
        val costOfPokemon = 50 * (position + 1)
        holder.cost.text = "₽$costOfPokemon"
        holder.buyButton.setOnClickListener {
            if (costOfPokemon < holder.dataAccess.currency) {
                println(mValues[position] + " has been bought!")
                val gson = Gson()
                val pkmn = holder.dataAccess.getPokemonObjectByName(mValues[position])
                val babyAbility = pkmn.abilities[0]
                val starterPokemon = OwnedPokemon(pkmn.name, babyAbility.ability.name,
                        pkmn.stats[1].baseStat, pkmn.stats[2].baseStat, pkmn.stats[3].baseStat,
                        pkmn.stats[4].baseStat, pkmn.stats[0].baseStat, pkmn.stats[5].baseStat,
                        0, 0, 0, 0, 0, 0, pkmn.id, pkmn.types[0].toString(), pkmn.types[0].toString(),
                        mValues[position])
                var firstEmptySlot = 1
                for (i in 1..7) {
                    val slot = holder.dataAccess.getPartySlot("" + i)
                    if (slot == "null") {
                        firstEmptySlot = i
                        break
                    }
                }
                holder.dataAccess.updatePartySlot("" + firstEmptySlot, gson.toJson(starterPokemon))
                var oldCurrency = holder.dataAccess.currency
                oldCurrency = oldCurrency - costOfPokemon
                holder.dataAccess.updateCurrency(oldCurrency)
            }
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(binding: FragmentPokemonListBinding) : RecyclerView.ViewHolder(binding.root) {
        val mContentView: TextView
        var buyButton: Button
        var mItem: String? = null
        var context: Context? = null
        var dataAccess: Database
        var cost: TextView
        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }

        init {
            val context = itemView.context
            mContentView = binding.content
            buyButton = binding.pokemonBuyButton
            cost = binding.costOfPokemon
            dataAccess = Database(context)
        }
    }
}