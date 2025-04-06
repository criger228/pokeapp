package com.minminas.pokeapp.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.minminas.pokeapp.data.model.PokemonResult

@Composable
fun PokemonItemRow(
    pokemon: PokemonResult,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(text = pokemon.name.uppercase(), style = MaterialTheme.typography.titleMedium)
        Text(text = pokemon.url)
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}
