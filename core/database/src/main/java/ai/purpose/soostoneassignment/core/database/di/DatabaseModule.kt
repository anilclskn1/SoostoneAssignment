package ai.purpose.soostoneassignment.core.database.di

import ai.purpose.soostoneassignment.core.database.SoostoneDatabase
import ai.purpose.soostoneassignment.core.database.SoostoneDatabase.Companion.DATABASE_NAME
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SoostoneDatabase {
        return Room.databaseBuilder(
            context,
            SoostoneDatabase::class.java,
            DATABASE_NAME
        )
            .createFromAsset("database/$DATABASE_NAME.db")
            .build()
    }
}