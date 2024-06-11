package com.example.recipeappfinalcase.feature.favorite

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipeappfinalcase.data.source.local.FavoriteRecipe
import com.example.recipeappfinalcase.feature.ui.theme.RecipeAppFinalCaseTheme

@Composable
fun FavoriteScreen(
    viewModel: FavoriteVM = hiltViewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val favoriteState = viewModel.favoriteState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Your favorite recipes are here!") },
            )
        },
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            favoriteState.value.recipe?.let {
                items(it) { recipe ->
                    FavoriteRecipeItem(viewModel, recipe)
                }
            }
        }
    }
}

@Composable
fun FavoriteRecipeItem(
    viewModel: FavoriteVM = hiltViewModel(),
    recipe: FavoriteRecipe,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = recipe.image,
                modifier = Modifier
                    .weight(1f),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(2.5f),
                text = recipe.title,
                style = MaterialTheme.typography.bodyMedium
            )
            IconButton(onClick = { viewModel.deleteFavoriteRecipe(recipe.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    RecipeAppFinalCaseTheme {
        FavoriteRecipeItem(
            recipe =
            FavoriteRecipe(
                id = 1,
                title = "Pasta",
                image = "https://www.themealdb.com/images/media/meals/58oia61564916529.jpg",
                summary = "jpg".repeat(100),
            )
        )
    }
}
