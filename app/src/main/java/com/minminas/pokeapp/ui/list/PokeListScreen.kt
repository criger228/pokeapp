package com.minminas.pokeapp.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.minminas.pokeapp.data.model.PokemonResult

@Composable
fun PokemonListScreen(
    viewModel: PokeListViewModel = viewModel(),
    onPokemonClick: (PokemonResult) -> Unit
) {
    val pokemonList by viewModel.pokemonList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMsg by viewModel.errorMsg.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            errorMsg != null -> {
                Text(text = "Error: $errorMsg", modifier = Modifier.padding(16.dp))
            }

            else -> {
                LazyColumn {
                    items(pokemonList) { pokemon ->
                        PokemonItemRow(
                            pokemon = pokemon,
                            onClick = { onPokemonClick(pokemon) }
                        )
                    }
                }
            }
        }
    }
}
