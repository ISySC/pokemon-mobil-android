package mx.com.pokemon.project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import mx.com.pokemon.project.R
import mx.com.pokemon.project.database.entities.PokemonEntity
import mx.com.pokemon.project.databinding.LayoutItemPokemonBinding
import java.util.*


class PokemonAdapter : RecyclerView.Adapter<PokemonViewHolder>(),

    Filterable {
    private val pokemons: MutableList<PokemonEntity> = mutableListOf()

    fun add(pokemons: List<PokemonEntity>) {
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }

    private var pokemonFilterList: List<PokemonEntity>

    init {
        pokemonFilterList = pokemons
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(
            parent.context,
            layoutInflater.inflate(R.layout.layout_item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemonFilterList[position]
        holder.bind(item);
    }

    override fun getItemCount(): Int = pokemonFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    pokemonFilterList = pokemons
                } else {
                    val resultList = ArrayList<PokemonEntity>()
                    for (row in pokemons) {
                        if (row.name?.lowercase(Locale.ROOT)
                                ?.contains(charSearch.lowercase(Locale.ROOT)) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    pokemonFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = pokemonFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                pokemonFilterList = results?.values as ArrayList<PokemonEntity>
                notifyDataSetChanged()
            }

        }
    }

}

class PokemonViewHolder(private var context: Context, view: View) : RecyclerView.ViewHolder(view) {

    private var binding = LayoutItemPokemonBinding.bind(view)

    fun bind(pokemon: PokemonEntity) {
        binding.tvName.text = pokemon.name!!.uppercase()
    }
}