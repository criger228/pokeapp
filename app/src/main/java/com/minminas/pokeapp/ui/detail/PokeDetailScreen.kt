package com.minminas.pokeapp.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun PokemonDetailScreen(
    name: String,
    viewModel: PokeDetailViewModel = viewModel()
) {
    val detail by viewModel.pokemonDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMsg by viewModel.error.collectAsState()

    LaunchedEffect(name) {
        viewModel.loadDetail(name)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            errorMsg != null -> Text(text = "Error: $errorMsg", modifier = Modifier.padding(16.dp))
            detail != null -> {
                val pokemon = detail!!
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Name: ${pokemon.name.uppercase()}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    pokemon.sprites.front_default?.let { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = pokemon.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(150.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "ID: ${pokemon.id}")
                }
            }

            else -> {}
        }
    }
}
