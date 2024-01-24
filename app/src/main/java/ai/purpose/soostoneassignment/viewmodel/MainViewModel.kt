package ai.purpose.soostoneassignment.viewmodel

import ai.purpose.soostoneassignment.domain.MainActivityUseCase
import ai.purpose.soostoneassignment.model.PokemonModel
import ai.purpose.soostoneassignment.view.SoostoneAppState
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: MainActivityUseCase
): ViewModel() {
    private val _uiState: MutableStateFlow<SoostoneAppState> = MutableStateFlow(SoostoneAppState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            useCase.getPokemonList().collectLatest { pokemonList ->
                Log.d("KKFKJJKFF", pokemonList.toString())
                _uiState.update {
                    it.copy(
                        pokemonList = pokemonList
                    )
                }
            }
        }
    }

    fun updateSelectedPokemon(selectedPokemon: PokemonModel?){
        _uiState.update {
            it.copy(
                selectedPokemon = selectedPokemon
            )
        }
    }


}