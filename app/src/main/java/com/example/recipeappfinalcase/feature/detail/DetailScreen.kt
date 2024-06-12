package com.example.recipeappfinalcase.feature.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeappfinalcase.feature.ui.theme.RecipeAppFinalCaseTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailVM = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            // TopAppBar
            TopAppBar(
                title = {
                    // title
                    Text(text = uiState.value.recipe?.title ?: "Recipe Detail", overflow = TextOverflow.Ellipsis, maxLines = 1)
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (uiState.value.isFavorite) {
                                viewModel.removeRecipeFromFavorites()
                            } else {
                                viewModel.addRecipeToFavorites()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (uiState.value.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (uiState.value.isFavorite) Color.Red else Color.Gray
                        )
                    }
                },
                navigationIcon = {
                    // back icon button
                    // back icon button
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
            )

        },
    )
    { paddingValues ->
        if (uiState.value.isLoading) {
            println("Loading")
        } else if (uiState.value.isError) {
            println("Error")
            Text("An error occurred")
        } else {
            println("Success")
            uiState.value.recipe?.let { recipe ->
                println(recipe.title)
                DetailComponent(recipe,paddingValues)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RecipeAppFinalCaseTheme {
        DetailScreen{

        }
    }
}


