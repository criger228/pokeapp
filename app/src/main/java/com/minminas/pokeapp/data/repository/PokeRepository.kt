package com.minminas.pokeapp.data.repository

import com.minminas.pokeapp.data.model.PokeListResponse
import com.minminas.pokeapp.data.model.PokemonDetail
import com.minminas.pokeapp.data.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokeRepository {

    suspend fun getPokemonList(page: Int = 0, limit: Int = 20): PokeListResponse =
        withContext(Dispatchers.IO) {
            val offset = page * limit
            RetrofitClient.api.getPokemonList(limit, offset)
        }

    suspend fun getPokemonDetail(name: String): PokemonDetail =
        withContext(Dispatchers.IO) {
            RetrofitClient.api.getPokemonDetail(name)
        }
}
