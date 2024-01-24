package ai.purpose.soostoneassignment.core.database.dao

import ai.purpose.soostoneassignment.core.database.model.PokemonEntity
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import java.sql.SQLException
import kotlin.jvm.Throws

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getPokemonList(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE id = :id")
    fun getPokemon(id: Long): PokemonEntity

    @Upsert
    @Throws(SQLException::class)
    suspend fun upsertPokemon(pokemon: PokemonEntity)

}