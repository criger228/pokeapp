package com.minminas.pokeapp.data.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val sprites: Sprites,

    // Nuevo campo
    val abilities: List<PokemonAbility>
)

data class PokemonAbility(
    val ability: AbilityInfo,
    val is_hidden: Boolean,
    val slot: Int
)

data class AbilityInfo(
    val name: String,
    val url: String
)
