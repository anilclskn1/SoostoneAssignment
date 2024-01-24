package ai.purpose.soostoneassignment.core.data.repository

import ai.purpose.soostoneassignment.core.data.api.PokemonApiService
import ai.purpose.soostoneassignment.core.data.model.Image
import ai.purpose.soostoneassignment.core.data.model.Pokemon
import ai.purpose.soostoneassignment.core.data.model.asExternalModel
import ai.purpose.soostoneassignment.core.database.dao.ImageDao
import ai.purpose.soostoneassignment.core.database.dao.PokemonDao
import ai.purpose.soostoneassignment.core.database.model.ImageEntity
import ai.purpose.soostoneassignment.core.database.model.PokemonEntity
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val imageDao: ImageDao,
    private val apiService: PokemonApiService
) : PokemonRepository {
    @SuppressLint("SuspiciousIndentation")
    override fun getPokemonList(): Flow<List<Pokemon>> {


        return flow {
            val a = pokemonDao.getPokemonList()
            Log.d("aaaaaaaa",a.toString())
            val localPokemonList = pokemonDao.getPokemonList().map { it.asExternalModel() }
            var emittedList: List<Pokemon> = emptyList()  // Variable to store the list for emission
            try {
                val apiResult = fetchPokemonListFromApi()
                val remotePokemonList = apiResult.getOrThrow()

                    if (localPokemonList.isEmpty()) {
                        remotePokemonList.forEach { pokemon ->
                            pokemonDao.upsertPokemon(
                                PokemonEntity(
                                    id = pokemon.id,
                                    name = pokemon.name,
                                    description = pokemon.description,
                                    imageUrl = pokemon.imageUrl
                                )
                            )
                            imageDao.upsertImage(
                                ImageEntity(
                                    id = pokemon.id,
                                    base64 = convertBitmapToBase64(downloadImage(pokemon.imageUrl))
                                )
                            )
                        }
                        emittedList = pokemonDao.getPokemonList().map { it.asExternalModel() }
                    } else if (localPokemonList.size < remotePokemonList.size) {
                        val missingPokemonList = remotePokemonList.subtract(localPokemonList.toSet())
                        missingPokemonList.forEach { missingPokemon ->
                            Log.d("New Data Found",missingPokemon.name)
                            pokemonDao.upsertPokemon(
                                PokemonEntity(
                                    id = missingPokemon.id,
                                    name = missingPokemon.name,
                                    description = missingPokemon.description,
                                    imageUrl = missingPokemon.imageUrl
                                )
                            )
                            imageDao.upsertImage(
                                ImageEntity(
                                    id = missingPokemon.id,
                                    base64 = convertBitmapToBase64(downloadImage(missingPokemon.imageUrl))
                                )
                            )
                            emittedList = pokemonDao.getPokemonList().map { it.asExternalModel() }
                        }
                    } else {
                        emittedList = localPokemonList
                    }

            }catch (e: IOException){
                Log.d("Exception",e.localizedMessage!!)
                emittedList = localPokemonList
            }
            emit(emittedList)
        }.flowOn(Dispatchers.IO)
    }

    override fun getPokemon(id: Long): Flow<Pokemon> {
        return flow {
            emit(
                pokemonDao.getPokemon(id).asExternalModel()
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun upsertPokemon(
        id: Long,
        name: String,
        explanation: String,
        url: String
    ): Result<Unit> {
        return runCatching {
            val pokemonList = pokemonDao.getPokemonList()
            if (pokemonList.any { it.name == name })
                return Result.failure(Exception())
            else
                pokemonDao.upsertPokemon(
                    PokemonEntity(
                        id = id,
                        name = name,
                        description = explanation,
                        imageUrl = url
                    )
                )
        }
    }

    override suspend fun fetchPokemonListFromApi(): Result<List<Pokemon>> {
        return runCatching {
            apiService.getPokemons()
        }
    }

    override fun getImageList(): Flow<List<Image>> {
        return flow {
            emit(
                imageDao.getImageList().map { it.asExternalModel() }
            )
        }.flowOn(Dispatchers.IO)
    }

    override fun getImage(id: Long): Flow<Image?> {
        return flow {
            emit(
                imageDao.getImage(id).asExternalModel()
            )
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun downloadImage(url: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val urlConnection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
            urlConnection.connect()

            val inputStream: InputStream = urlConnection.inputStream
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private suspend fun convertBitmapToBase64(bitmap: Bitmap?): String = withContext(Dispatchers.Default) {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }



}