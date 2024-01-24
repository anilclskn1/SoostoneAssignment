package ai.purpose.soostoneassignment.core.data.model

import ai.purpose.soostoneassignment.core.database.model.PokemonEntity

data class Pokemon(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)

internal fun PokemonEntity.asExternalModel() = Pokemon(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl
)