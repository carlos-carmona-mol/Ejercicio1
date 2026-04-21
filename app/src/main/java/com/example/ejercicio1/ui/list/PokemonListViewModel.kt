package com.example.ejercicio1.ui.list

import androidx.lifecycle.*
import com.example.ejercicio1.data.PokemonRepository
import com.example.ejercicio1.data.remote.model.PokemonResult
import com.example.ejercicio1.ui.common.UiState
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _uiState = MutableLiveData<UiState<List<PokemonResult>>>()
    val uiState: LiveData<UiState<List<PokemonResult>>> = _uiState

    private var currentPage = 0
    private val limit = 20

    init {
        loadPage(0)
    }

    fun loadPage(page: Int) {
        _uiState.value = UiState.Loading
        currentPage = page

        viewModelScope.launch {
            try {
                val response = repository.getPokemonList(limit, page * limit)
                if (response.isSuccessful) {
                    val body = response.body()
                    val results = body?.results ?: emptyList()

                    if (results.isEmpty()) {
                        _uiState.value = UiState.Empty
                    } else {
                        _uiState.value = UiState.Success(results)
                    }
                } else {
                    _uiState.value = UiState.Error("Error al cargar la lista")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error de red: ${e.message}")
            }
        }
    }

    fun retry() {
        loadPage(currentPage)
    }

    fun nextPage() {
        if (currentPage < 2) { // mínimo 3 páginas (0,1,2)
            loadPage(currentPage + 1)
        }
    }

    fun previousPage() {
        if (currentPage > 0) {
            loadPage(currentPage - 1)
        }
    }
}
