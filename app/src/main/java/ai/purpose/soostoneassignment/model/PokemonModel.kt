package ai.purpose.soostoneassignment.model

import androidx.compose.ui.graphics.ImageBitmap

data class PokemonModel(
    val id: Long,
    val name: String,
    val description: String,
    val url: String,
    val imageBitmap: ImageBitmap?
)