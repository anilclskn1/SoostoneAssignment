package ai.purpose.soostoneassignment.core.data.di

import ai.purpose.soostoneassignment.core.data.api.PokemonApiService
import ai.purpose.soostoneassignment.core.data.repository.PokemonRepository
import ai.purpose.soostoneassignment.core.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    @Binds
    fun bindsPokemonRepository(
        pokemonRepository: PokemonRepositoryImpl
    ): PokemonRepository

    companion object {
        @Provides
        fun providePokemonApiService(retrofit: Retrofit): PokemonApiService {
            return retrofit.create(PokemonApiService::class.java)
        }
        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
