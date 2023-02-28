package mx.com.pokemon.project.ui.home

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import mx.com.pokemon.project.adapters.PokemonAdapter
import mx.com.pokemon.project.data.model.PokemonListModel
import mx.com.pokemon.project.data.model.PokemonResultModel
import mx.com.pokemon.project.database.entities.PokemonEntity
import mx.com.pokemon.project.databinding.HomeActivityBinding
import mx.com.pokemon.project.utils.Utils

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: HomeActivityBinding
    private lateinit var homeViewModel: HomeViewModel

    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitView()
        setInitObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.ivList.setOnClickListener {
            homeViewModel.setShowModeList(true)
        }

        binding.ivGrid.setOnClickListener {
            homeViewModel.setShowModeList(false)
        }
    }

    private fun setInitView() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PokemonAdapter()
    }

    private fun setInitObservers() {
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]

        homeViewModel.setShowModeList(true)

        lifecycleScope.launch {
            Utils.dbConnect().pokemonDAO().getPokemonList().observe(this@HomeActivity) { it ->
                if (it != null) {
                    if (it.isEmpty())
                        homeViewModel.getPokemonList()
                    else {
                        var result = it.map { PokemonResultModel(it.name, it.url) }
                        homeViewModel.pokemonListModel.value =
                            PokemonListModel(null, null, null, result)
                    }
                }
            }
        }

        homeViewModel.pokemonListModel.observe(this@HomeActivity) { it ->
            if (it != null) {
                if (it.results.isNotEmpty()) {
                    setPokemonList(it.results.map { PokemonEntity(0, it.name, it.url) })
                    lifecycleScope.launch {
                        Utils.dbConnect().pokemonDAO().insertPokemonToList(it.results.map {
                            PokemonEntity(
                                0,
                                it.name,
                                it.url
                            )
                        })
                    }
                }
            }
        }

        homeViewModel.isListView.observe(this@HomeActivity) {
            if (it == true) {
                binding.lvPokemones.layoutManager = LinearLayoutManager(this)
            } else {
                binding.lvPokemones.layoutManager = GridLayoutManager(this,2)
            }

            binding.lvPokemones.adapter = adapter
        }
    }

    private fun setPokemonList(pokemons: List<PokemonEntity>) {
        binding.svPokemon.isEnabled = true

        /*if (binding.viewSwitch.displayedChild == 1)
            binding.viewSwitch.showPrevious()*/

        adapter.add(pokemons = pokemons)

        binding.svPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0.toString())
                return true
            }
        })

        binding.lvPokemones.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    homeViewModel.getPokemonList()
                }
            }
        })
    }
}