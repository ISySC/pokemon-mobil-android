package mx.com.pokemon.project.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResultModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)


