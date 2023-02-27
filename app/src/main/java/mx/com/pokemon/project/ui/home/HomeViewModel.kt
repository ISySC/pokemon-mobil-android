package mx.com.pokemon.project.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import mx.com.pokemon.project.data.model.PokemonListModel
import mx.com.pokemon.project.network.ApiService
import mx.com.pokemon.project.repository.HomeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    private val homeRepository = HomeRepository()
    val pokemonListModel = MutableLiveData<PokemonListModel>()
    private val connectionError = MutableLiveData<Boolean>()

    private val _isListView = MutableLiveData(true)
    val isListView: LiveData<Boolean> = _isListView


    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        connectionError.postValue(true)
    }

    fun getPokemonList() {
        var offset = 0
        var limit = 20

        try {
            if (pokemonListModel.value != null)
                if (pokemonListModel.value!!.results.isNotEmpty()) {
                    offset = pokemonListModel.value!!.results.size
                    limit += pokemonListModel.value!!.results.size
                }

            viewModelScope.launch(exceptionHandler) {
                val pokemonRequest =
                    homeRepository.getPokemons("pokemon?offset=$offset&limit=$limit")

                if (pokemonRequest.isSuccessful) {
                    pokemonListModel.postValue(pokemonRequest.body())
                } else connectionError.postValue(true)

            }
        } catch (e: Exception) {
            connectionError.postValue(true)
        }
    }

    fun setShowModeList(setShowModeList: Boolean){
        _isListView.value = setShowModeList
    }
}