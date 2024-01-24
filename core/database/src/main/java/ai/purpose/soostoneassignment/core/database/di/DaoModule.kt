package ai.purpose.soostoneassignment.core.database.di

import ai.purpose.soostoneassignment.core.database.SoostoneDatabase
import ai.purpose.soostoneassignment.core.database.dao.ImageDao
import ai.purpose.soostoneassignment.core.database.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun providesPokemonDao(
        database: SoostoneDatabase
    ): PokemonDao = database.getPokemonDao()

    @Provides
    @Singleton
    fun providesImageDao(
        database: SoostoneDatabase
    ): ImageDao = database.getImageDao()
}