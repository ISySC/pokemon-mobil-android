package mx.com.pokemon.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.com.pokemon.project.database.dao.PokemonDAO
import mx.com.pokemon.project.database.entities.PokemonEntity


@Database(
    entities = [PokemonEntity::class],
    version = 1
)

abstract class PokemonInstanceDB : RoomDatabase() {
    abstract fun pokemonDAO(): PokemonDAO

    companion object {
        @Volatile
        private var instance: PokemonInstanceDB? = null

        fun getInstance(context: Context): PokemonInstanceDB {
            if (instance != null)
                return instance as PokemonInstanceDB

            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    PokemonInstanceDB::class.java,
                    "POKEMONDB.db"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
    }
}