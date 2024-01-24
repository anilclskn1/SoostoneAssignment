package ai.purpose.soostoneassignment.core.database

import ai.purpose.soostoneassignment.core.database.dao.ImageDao
import ai.purpose.soostoneassignment.core.database.dao.PokemonDao
import ai.purpose.soostoneassignment.core.database.model.ImageEntity
import ai.purpose.soostoneassignment.core.database.model.PokemonEntity
import ai.purpose.soostoneassignment.core.database.model.UrlConverter
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        PokemonEntity::class,
        ImageEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class SoostoneDatabase : RoomDatabase() {
    abstract fun getPokemonDao() : PokemonDao
    abstract fun getImageDao() : ImageDao

    companion object {
        const val DATABASE_NAME = "soostone"
        const val ID_ = "id"
    }
}