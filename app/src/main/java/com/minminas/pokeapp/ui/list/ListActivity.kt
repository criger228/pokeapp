package com.minminas.pokeapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.minminas.pokeapp.data.model.PokemonResult
import com.minminas.pokeapp.ui.detail.DetailActivity

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonListScreen(
                onPokemonClick = { pokemon: PokemonResult ->
                    // Abre DetailActivity
                    val intent = Intent(this, DetailActivity::class.java).apply {
                        putExtra("pokeName", pokemon.name)
                    }
                    startActivity(intent)
                }
            )
        }
    }
}
