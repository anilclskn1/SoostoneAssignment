package ai.purpose.soostoneassignment.core.database.model

import ai.purpose.soostoneassignment.core.database.SoostoneDatabase
import ai.purpose.soostoneassignment.core.database.model.PokemonEntity.Companion.POKEMON_TABLE_NAME
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.net.URL

@Entity(
    tableName = POKEMON_TABLE_NAME,
    indices = [
        Index(
            value = ["name"],
            unique = true
        )
    ]
)

data class PokemonEntity (
    @ColumnInfo(name = SoostoneDatabase.ID_)
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = POKEMON_NAME, collate = ColumnInfo.NOCASE)
    val name: String,
    @ColumnInfo(name = POKEMON_EXPLANATION, collate = ColumnInfo.NOCASE)
    val description: String,
    @ColumnInfo(name = POKEMON_IMAGE)
    val imageUrl: String

){
    companion object {
        const val POKEMON_TABLE_NAME = "pokemon"
        const val POKEMON_NAME = "name"
        const val POKEMON_EXPLANATION = "description"
        const val POKEMON_IMAGE = "imageUrl"
    }
}