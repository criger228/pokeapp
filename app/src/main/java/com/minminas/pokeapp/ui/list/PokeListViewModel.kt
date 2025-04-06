package com.minminas.pokeapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minminas.pokeapp.data.model.PokemonResult
import com.minminas.pokeapp.data.repository.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeListViewModel : ViewModel() {

    private val repository = PokeRepository()

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>(null)
    val errorMsg = _errorMsg.asStateFlow()

    init {
        loadPokemons(page = 0)
    }

    fun loadPokemons(page: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getPokemonList(page)
                _pokemonList.value = response.results
                _errorMsg.value = null
            } catch (e: Exception) {
                _errorMsg.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }
}
