package com.example.recipeappfinalcase.feature.home

import com.example.recipeappfinalcase.data.model.RecipeResponse

data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val recipes: RecipeResponse? = null
)