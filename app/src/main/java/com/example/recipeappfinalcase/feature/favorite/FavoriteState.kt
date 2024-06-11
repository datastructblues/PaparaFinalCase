package com.example.recipeappfinalcase.feature.favorite

import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.data.source.local.FavoriteRecipe

data class FavoriteState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val recipe: List<FavoriteRecipe>? = null
)
