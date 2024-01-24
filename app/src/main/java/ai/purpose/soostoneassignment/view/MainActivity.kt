package ai.purpose.soostoneassignment.view

import ai.purpose.soostoneassignment.core.ui.components.ListRow
import ai.purpose.soostoneassignment.core.ui.components.MainScreen
import ai.purpose.soostoneassignment.model.PokemonModel
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ai.purpose.soostoneassignment.ui.theme.SoostoneAssignmentTheme
import ai.purpose.soostoneassignment.viewmodel.MainViewModel
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoostoneAssignmentTheme {
                val uiState = viewModel.uiState.collectAsState()
                MainScreen(topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = when(uiState.value.selectedPokemon){
                                    null -> "Pokemon"
                                    else -> uiState.value.selectedPokemon!!.name
                                }
                            )
                        },
                        navigationIcon = {
                            when(uiState.value.selectedPokemon) {
                                null -> {}
                                else -> {
                                    IconButton(
                                        onClick = {
                                            viewModel.updateSelectedPokemon(null)
                                        }
                                    ) {
                                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                                    }
                                }
                            }
                        }
                    )
                }) {

                    when(uiState.value.selectedPokemon){
                        null -> {
                            PokemonListScreen(
                                pokemonList = uiState.value.pokemonList ?: mutableListOf(),
                                updateSelectedPokemon = viewModel::updateSelectedPokemon
                            )
                        }
                        else -> {
                            PokemonDetailScreen(
                                pokemon = uiState.value.selectedPokemon!!
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonListScreen(
    pokemonList: List<PokemonModel>,
    updateSelectedPokemon: (PokemonModel) -> Unit
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .height(200.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(pokemonList){
            ListRow(
                modifier = Modifier
                    .clickable { updateSelectedPokemon(it) },
                imageBitmap = it.imageBitmap ?: ImageBitmap(20,20),
                name = it.name,
                description = it.description
            )
        }
    }
}

@Composable
fun PokemonDetailScreen(
    pokemon: PokemonModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(150.dp),
            bitmap = pokemon.imageBitmap ?: ImageBitmap(20, 20),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = pokemon.description,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SoostoneAssignmentTheme {
        PokemonListScreen(
            pokemonList = mutableListOf(),
            updateSelectedPokemon = {}
        )
    }
}