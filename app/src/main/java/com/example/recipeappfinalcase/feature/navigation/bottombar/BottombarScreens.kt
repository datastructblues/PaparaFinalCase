package com.example.recipeappfinalcase.feature.navigation.bottombar

import com.example.recipeappfinalcase.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int? = null
) {
    data object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.home,
        icon_focused = R.drawable.home
    )
    data object Favorite: BottomBarScreen(
        route = "favorite",
        title = "Favorite",
        icon = R.drawable.favorite,
        icon_focused = R.drawable.favorite
    )
}
