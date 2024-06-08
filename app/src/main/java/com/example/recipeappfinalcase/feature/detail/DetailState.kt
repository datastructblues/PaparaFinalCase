package com.example.recipeappfinalcase.feature.detail

import com.example.recipeappfinalcase.data.model.detail.DetailResponse

data class DetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val recipe: DetailResponse? = null
)