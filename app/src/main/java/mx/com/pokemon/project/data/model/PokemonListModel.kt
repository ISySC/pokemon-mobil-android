package mx.com.pokemon.project.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListModel(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: List<PokemonResultModel> = arrayListOf()
)
