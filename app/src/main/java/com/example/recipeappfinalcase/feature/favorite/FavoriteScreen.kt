package com.example.recipeappfinalcase.feature.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipeappfinalcase.data.source.local.FavoriteRecipe

@Composable
fun FavoriteScreen(
    viewModel: FavoriteVM = hiltViewModel(),
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val favoriteState = viewModel.favoriteState.collectAsState()
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) { paddingValues ->
        println(paddingValues)
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Your favorite recipes",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                favoriteState.value.recipe?.let {
                    items(it) { recipe ->
                        FavoriteRecipeItem(viewModel, recipe, navController)
                    }
                }
            }
        }
    }
}



@Composable
fun FavoriteRecipeItem(
    viewModel: FavoriteVM = hiltViewModel(),
    recipe: FavoriteRecipe,
    navController: NavHostController,
) {

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.tertiary,
        contentColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("detail/${recipe.id}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
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
                color = MaterialTheme.colorScheme.background,
                style = MaterialTheme.typography.bodyMedium
            )
            IconButton(onClick = { viewModel.deleteFavoriteRecipe(recipe.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}