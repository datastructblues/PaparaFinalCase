package com.example.recipeappfinalcase.feature.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeappfinalcase.feature.navigation.Screen
import com.example.recipeappfinalcase.feature.ui.components.LoadingComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeVM = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRecipes()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Home")
            },
                actions = {
                    TextButton(onClick = {
                        viewModel.fetchRecipes()
                    }) {
                        Text(text = "Refresh")
                    }
                }

            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center,
        ){
            if (state.value.isLoading) {
                LoadingComponent()
            }else{
                ListingComponent(
                    paddingValues = paddingValues,
                    recipes = state.value.recipes?.recipeList ?: emptyList(),
                ) { recipeId ->
                    navController.navigate(Screen.Detail.createRoute(recipeId))
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen(
        viewModel = hiltViewModel(),
    )
}

