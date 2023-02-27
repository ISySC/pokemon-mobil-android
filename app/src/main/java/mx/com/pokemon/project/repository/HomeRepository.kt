package mx.com.pokemon.project.repository

import mx.com.pokemon.project.data.model.PokemonListModel
import mx.com.pokemon.project.network.ApiClient
import mx.com.pokemon.project.network.UrlBaseService
import retrofit2.Response

class HomeRepository {
    var apiRequest = ApiClient().provideRetrofit()

    suspend fun getPokemons(queryString: String) : Response<PokemonListModel>{
        return apiRequest.getPokemonList(UrlBaseService.getUrlBase() + queryString)
    }
}