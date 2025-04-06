package com.minminas.pokeapp.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("pokeName") ?: ""

        setContent {
            PokemonDetailScreen(
                name = name,
                onBackClick = { finish() }
            )
        }
    }
}