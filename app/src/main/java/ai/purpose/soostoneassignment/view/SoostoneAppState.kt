package ai.purpose.soostoneassignment.view

import ai.purpose.soostoneassignment.model.PokemonModel

data class SoostoneAppState(
    val pokemonList: List<PokemonModel>? = null,
    val selectedPokemon: PokemonModel? = null
)