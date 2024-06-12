package com.example.recipeappfinalcase.feature.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.utils.parallaxLayoutModifier
import com.example.recipeappfinalcase.utils.parseToText
import kotlin.math.min


@Composable
fun DetailComponent(
    paddingValues: PaddingValues,
    recipe: DetailResponse,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        RecipeImage(recipe.image, recipe.title, scrollState)
        RecipeTitle(recipe.title)
        RecipeInstructions(recipe.instructions)
    }
}

@Composable
fun RecipeImage(image: String, title: String, scrollState: ScrollState) {
    AsyncImage(
        model = image,
        contentDescription = title,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .parallaxLayoutModifier(scrollState, 2)
            .fillMaxWidth()
            .absoluteOffset(y = -(scrollState.value * 0.1f).dp)
            .alpha(min(1f, 1 - (scrollState.value / 600f)))
    )
}

@Composable
fun RecipeTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(top = 16.dp)
    )
}

@Composable
fun RecipeInstructions(instructions: String) {
    Text(
        text = instructions.parseToText(),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 8.dp)
    )
}