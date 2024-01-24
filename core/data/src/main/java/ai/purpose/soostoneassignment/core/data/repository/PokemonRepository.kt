package ai.purpose.soostoneassignment.core.data.repository

import ai.purpose.soostoneassignment.core.data.model.Image
import ai.purpose.soostoneassignment.core.data.model.Pokemon
import kotlinx.coroutines.flow.Flow
import java.net.URL
import java.util.Base64

interface PokemonRepository {

    fun getPokemonList(
    ): Flow<List<Pokemon>>

    fun getPokemon(
        id: Long
    ): Flow<Pokemon>

    suspend fun upsertPokemon(
        id:Long,
        name: String,
        explanation: String,
        url: String
    ): Result<Unit>

    suspend fun fetchPokemonListFromApi(
    ): Result<List<Pokemon>>

    fun getImageList(
    ): Flow<List<Image>>

    fun getImage(
        id: Long
    ): Flow<Image?>

}