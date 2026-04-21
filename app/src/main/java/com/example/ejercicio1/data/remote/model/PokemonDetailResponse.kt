package com.example.ejercicio1.data.remote.model

import com.squareup.moshi.Json

data class PokemonDetailResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: Int,
    @Json(name = "weight") val weight: Int,
    @Json(name = "sprites") val sprites: Sprites,
    @Json(name = "types") val types: List<TypeSlot>
)

data class Sprites(
    @Json(name = "front_default") val frontDefault: String?
)

data class TypeSlot(
    @Json(name = "type") val type: TypeInfo
)

data class TypeInfo(
    @Json(name = "name") val name: String
)
