package ai.purpose.soostoneassignment.core.data.model

import ai.purpose.soostoneassignment.core.database.model.ImageEntity
import ai.purpose.soostoneassignment.core.database.model.PokemonEntity

data class Image(
    val id: Long,
    val base64: String?,
)

internal fun ImageEntity.asExternalModel() = Image(
    id = id,
    base64 = base64
)
