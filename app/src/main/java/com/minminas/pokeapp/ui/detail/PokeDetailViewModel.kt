package com.minminas.pokeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minminas.pokeapp.data.model.PokemonDetail
import com.minminas.pokeapp.data.repository.PokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokeDetailViewModel : ViewModel() {
    private val repo = PokeRepository()

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail = _pokemonDetail.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadDetail(name: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val detail = repo.getPokemonDetail(name)
                _pokemonDetail.value = detail
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }
}
