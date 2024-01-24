package ai.purpose.soostoneassignment.core.database.dao

import ai.purpose.soostoneassignment.core.database.model.ImageEntity
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import java.sql.SQLException
import kotlin.jvm.Throws

@Dao
interface ImageDao {

    @Query("SELECT * FROM images")
    fun getImageList(): List<ImageEntity>

    @Query("SELECT * FROM images WHERE id = :id")
    fun getImage(id: Long): ImageEntity

    @Upsert
    @Throws(SQLException::class)
    suspend fun upsertImage(image: ImageEntity)

}