package com.example.ejercicio1.data

import com.example.ejercicio1.data.remote.RetrofitInstance
import com.example.ejercicio1.data.remote.model.PokemonDetailResponse
import com.example.ejercicio1.data.remote.model.PokemonListResponse
import retrofit2.Response

class PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Response<PokemonListResponse> {
        return RetrofitInstance.api.getPokemonList(limit, offset)
    }

    suspend fun getPokemonDetail(id: Int): Response<PokemonDetailResponse> {
        return RetrofitInstance.api.getPokemonDetail(id)
    }
}
