package ai.purpose.soostoneassignment.domain

import ai.purpose.soostoneassignment.core.data.repository.PokemonRepository
import ai.purpose.soostoneassignment.model.PokemonModel
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class MainActivityUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    fun getPokemonList(): Flow<List<PokemonModel>> {
        return pokemonRepository.getPokemonList()
            .map { pokemonList ->
                pokemonList.map { pokemon ->
                    PokemonModel(
                        id = pokemon.id,
                        name = pokemon.name,
                        description = pokemon.description,
                        url = pokemon.imageUrl,
                        imageBitmap = base64ToImageBitmap(getImage(id = pokemon.id) ?: "")
                    )
                }
            }.flowOn(Dispatchers.IO)
    }

    private suspend fun getImage(id: Long): String? {
        return pokemonRepository.getImage(id)
            .firstOrNull()?.base64
    }

    @OptIn(ExperimentalEncodingApi::class)
    private suspend fun base64ToImageBitmap(base64String: String): ImageBitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val base64Image = base64String.replace(Regex("[^A-Za-z0-9+/=]"), "")

                val decodedByteArray: ByteArray = Base64.decode(base64Image)

                val bitmap =
                    BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)

                bitmap?.asImageBitmap()
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("ImageDecodingError", "Error decoding image: $e")
                null
            }
        }
    }
}