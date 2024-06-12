package com.example.recipeappfinalcase.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipeappfinalcase.R
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.utils.parallaxLayoutModifier
import com.example.recipeappfinalcase.utils.parseToText
import kotlin.math.min


@Composable
fun DetailComponent(
    recipe: DetailResponse,
    paddingValues: PaddingValues
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box{ RecipeImage(recipe.image, recipe.title, scrollState) }
        RecipeDetailCard(recipe = recipe)
        Spacer(modifier = Modifier.height(8.dp))
        RecipeSummary(recipe.summary)
        RecipeInstructions(recipe.instructions)
    }
}

@Composable
fun RecipeDetailCard(
    recipe: DetailResponse,
) {

    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp).absoluteOffset(y = (-50).dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = recipe.title,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Row(
                modifier = Modifier.padding(top = 8.dp, start = 4.dp, end = 4.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.time), contentDescription = "Time")
                Text(text = recipe.readyInMinutes.toString() + " min")
                Image(
                    painter = painterResource(id = R.drawable.health),
                    contentDescription = "Health Score"
                )
                Text(text = recipe.healthScore.toString() + " Health")
                Image(
                    painter = painterResource(id = R.drawable.serving),
                    contentDescription = "Servings"
                )
                Text(text = recipe.servings.toString() + " Servings")
            }
        }

    }

}

@Composable
fun RecipeImage(image: String, title: String, scrollState: ScrollState) {
    AsyncImage(
        model = image,
        contentDescription = title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .parallaxLayoutModifier(scrollState, 2)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .absoluteOffset(y = -(scrollState.value * 0.1f).dp)
            .alpha(min(1f, 1 - (scrollState.value / 600f)))
    )
}

@Composable
fun RecipeSummary(summary: String) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Summary",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = summary.parseToText(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

}

@Composable
fun RecipeInstructions(instructions: String) {
    Card(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
        )
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Ingredients",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = instructions.parseToText(),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}