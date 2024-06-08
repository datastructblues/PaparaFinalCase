package com.example.recipeappfinalcase.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipeappfinalcase.data.model.Recipe


@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    recipe : Recipe,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
    ){
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = recipe.image,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(16.dp)
                    .clip(CircleShape),
                contentDescription = recipe.title,
            )
            Text(
                text = recipe.title,
                modifier = Modifier
                    .padding(16.dp,8.dp),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeItemPreview() {
    RecipeItem(
        recipe = Recipe(
            id = 1,
            title = "Grilled Chicken",
            image = "https://www.themealdb.com/images/media/meals/58oia61564916529.jpg",
            imageType = "jpg",
        ),
        modifier = Modifier
        )
}
