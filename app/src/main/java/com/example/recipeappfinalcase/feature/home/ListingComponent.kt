package com.example.recipeappfinalcase.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipeappfinalcase.data.model.Recipe
import com.example.recipeappfinalcase.feature.ui.theme.RecipeAppFinalCaseTheme

@Composable
fun ListingComponent(
    recipes: List<Recipe>,
    onRecipeClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(count = recipes.size) {
            RecipeItem(
                recipe = recipes[it],
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onRecipeClick(recipes[it].id)
                    }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListingComponenetPreview() {
    RecipeAppFinalCaseTheme {
        ListingComponent(
            recipes = listOf(
                Recipe(
                    id = 1,
                    title = "Recipe 1",
                    imageType = "jpg",
                    image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
                ),
                Recipe(
                    id = 2,
                    title = "Recipe 2",
                    imageType = "jpg",
                    image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
                ),
                Recipe(
                    id = 3,
                    title = "Recipe 3",
                    imageType = "jpg",
                    image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
                ),
                Recipe(
                    id = 4,
                    title = "Recipe 4",
                    imageType = "jpg",
                    image = "https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg",
                ),
            )
        ) {

        }
    }
}