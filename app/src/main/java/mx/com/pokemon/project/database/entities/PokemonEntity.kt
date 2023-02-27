package mx.com.pokemon.project.database.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import mx.com.pokemon.project.database.entities.entityNames.TableName

@Entity(tableName = TableName.PokemonEntityName)
data class PokemonEntity(
    @Expose(serialize = false) @PrimaryKey(autoGenerate = true) val id: Int,
    var name : String? = null,
    var url  : String? = null
)