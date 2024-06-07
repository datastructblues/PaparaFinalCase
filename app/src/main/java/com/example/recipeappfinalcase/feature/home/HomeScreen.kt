package com.example.recipeappfinalcase.feature.home


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeappfinalcase.feature.ui.components.ListingComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeVM = hiltViewModel(),
    onRecipeClick: (Int) -> Unit,
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
        ListingComponent(
            paddingValues = paddingValues,
            recipes = state.value.recipes?.recipeList?: emptyList(),
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeScreen(
        viewModel = hiltViewModel(),
        onRecipeClick = {}
    )
}

