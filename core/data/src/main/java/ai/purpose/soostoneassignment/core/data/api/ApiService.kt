package ai.purpose.soostoneassignment.core.data.api

import ai.purpose.soostoneassignment.core.data.model.Pokemon
import retrofit2.http.GET

interface PokemonApiService {
    @GET("DavidCorrado/8912aa29d7c4a5fbf03993b32916d601/raw/")
    //@GET("anilclskn1/57a9d138efa689bdc661fd917f88723a/raw/")
    suspend fun getPokemons(): List<Pokemon>
}
