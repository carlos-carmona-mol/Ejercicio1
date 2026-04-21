package com.example.ejercicio1.data.remote.model

import com.squareup.moshi.Json

data class PokemonListResponse(
    @Json(name = "count") val count: Int,
    @Json(name = "results") val results: List<PokemonResult>
)

data class PokemonResult(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
) {
    val id: Int
        get() = url.trimEnd('/').split("/").last().toInt()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
