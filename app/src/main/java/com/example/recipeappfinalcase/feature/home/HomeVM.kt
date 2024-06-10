package com.example.recipeappfinalcase.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappfinalcase.data.repository.RecipeRepository
import com.example.recipeappfinalcase.data.source.network.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val repository: RecipeRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    val query = MutableStateFlow("")


    init {
        viewModelScope.launch {
            query
                .debounce(1000L)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .collect { fetchRecipes(it) }
        }
    }

    fun fetchRecipes(
        query: String? = null
    ) {
        val internetOnline = true
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true)
            }
            if (internetOnline) {
                repository.getRecipes(10, 0,query).collect { networkState ->
                    _uiState.update { state ->
                        when (networkState) {
                            is NetworkState.Loading -> state.copy(isLoading = true)
                            is NetworkState.Success -> state.copy(isLoading = false, isError = false, recipes = networkState.data)
                            is NetworkState.Error -> state.copy(isLoading = false, isError = true)
                        }
                    }
                }
            }
        }
    }
}