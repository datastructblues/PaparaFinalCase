package com.example.recipeappfinalcase.feature.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    data object Favorite : Screen("favorite")
}