package com.example.recipeappfinalcase.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeappfinalcase.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteVM @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel(){

    private val _favoriteState = MutableStateFlow(FavoriteState())
    val favoriteState :StateFlow<FavoriteState> = _favoriteState

    init {
        fetchFavoriteRecipe()
    }

    private fun fetchFavoriteRecipe(){
        viewModelScope.launch {
            recipeRepository.getFavoriteRecipes().collect { favoriteRecipe ->
                _favoriteState.value = FavoriteState(isLoading = false, isError = false, recipe = favoriteRecipe)
            }
        }
    }

    fun deleteFavoriteRecipe(id: Int){
        viewModelScope.launch {
            recipeRepository.deleteFavoriteRecipe(id)
        }
    }

}