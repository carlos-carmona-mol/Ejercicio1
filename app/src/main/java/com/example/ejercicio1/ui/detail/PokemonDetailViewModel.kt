package com.example.ejercicio1.ui.detail

import androidx.lifecycle.*
import com.example.ejercicio1.data.PokemonRepository
import com.example.ejercicio1.data.remote.model.PokemonDetailResponse
import com.example.ejercicio1.ui.common.UiState
import kotlinx.coroutines.launch

class PokemonDetailViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _uiState = MutableLiveData<UiState<PokemonDetailResponse>>()
    val uiState: LiveData<UiState<PokemonDetailResponse>> = _uiState

    fun loadPokemon(id: Int) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val response = repository.getPokemonDetail(id)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _uiState.value = UiState.Success(body)
                    } else {
                        _uiState.value = UiState.Error("Detalle vacío")
                    }
                } else {
                    _uiState.value = UiState.Error("Error al cargar detalle")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error de red: ${e.message}")
            }
        }
    }

    fun retry(id: Int) {
        loadPokemon(id)
    }
}
