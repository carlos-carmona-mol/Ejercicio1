package com.example.ejercicio1.data.remote

import com.example.ejercicio1.data.remote.model.PokemonDetailResponse
import com.example.ejercicio1.data.remote.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonListResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): Response<PokemonDetailResponse>
}
