package ai.purpose.soostoneassignment.core.database.model

import ai.purpose.soostoneassignment.core.database.SoostoneDatabase
import ai.purpose.soostoneassignment.core.database.model.ImageEntity.Companion.IMAGE_TABLE_NAME
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = IMAGE_TABLE_NAME
)

data class ImageEntity (
    @ColumnInfo(name = SoostoneDatabase.ID_)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = IMAGE_BASE64STRING)
    val base64: String?

){
    companion object {
        const val IMAGE_TABLE_NAME = "images"
        const val IMAGE_BASE64STRING = "base64"
    }
}