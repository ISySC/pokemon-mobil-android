package mx.com.pokemon.project.network

import mx.com.pokemon.project.data.model.PokemonListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPokemonList(@Url url: String) : Response<PokemonListModel>
}