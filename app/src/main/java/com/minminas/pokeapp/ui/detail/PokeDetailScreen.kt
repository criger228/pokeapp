package com.minminas.pokeapp.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    name: String,
    onBackClick: () -> Unit,
    viewModel: PokeDetailViewModel = viewModel()
) {
    val detail by viewModel.pokemonDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMsg by viewModel.error.collectAsState()

    LaunchedEffect(name) {
        viewModel.loadDetail(name)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Pokémon") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                isLoading -> CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                errorMsg != null -> Text(text = "Error: $errorMsg")
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
                        Text(
                            text = "Abilities:",
                            style = MaterialTheme.typography.titleMedium
                        )
                        pokemon.abilities.forEach { abilityItem ->
                            Text(" - ${abilityItem.ability.name}")
                        }
                    }
                }
            }
        }
    }
}