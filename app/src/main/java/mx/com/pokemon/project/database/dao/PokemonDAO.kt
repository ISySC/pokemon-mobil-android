package mx.com.pokemon.project.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.com.pokemon.project.database.entities.PokemonEntity
import mx.com.pokemon.project.database.entities.entityNames.TableName

@Dao
interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonToList(places: List<PokemonEntity>)

    @Query("delete from ${TableName.PokemonEntityName}")
    fun removePokemonList()

    @Query("select * from ${TableName.PokemonEntityName}")
    fun getPokemonList(): LiveData<List<PokemonEntity>>

}