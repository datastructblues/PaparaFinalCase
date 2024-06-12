package com.example.recipeappfinalcase.feature.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeappfinalcase.feature.detail.DetailScreen
import com.example.recipeappfinalcase.feature.favorite.FavoriteScreen
import com.example.recipeappfinalcase.feature.home.HomeScreen
import com.example.recipeappfinalcase.feature.navigation.bottombar.BottomBarScreen



@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val items = listOf(BottomBarScreen.Home, BottomBarScreen.Favorite)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(painterResource(id = screen.icon), contentDescription = screen.title)
                        },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                if (id != null) {
                    DetailScreen(onBack = { navController.popBackStack() })
                }
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(navController = navController)
            }
        }
    }
}