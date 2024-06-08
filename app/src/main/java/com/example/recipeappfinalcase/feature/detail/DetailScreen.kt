package com.example.recipeappfinalcase.feature.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.feature.ui.theme.RecipeAppFinalCaseTheme
import kotlin.math.min


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
                    Text(text = uiState.value.recipe?.title ?: "Recipe Detail")
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
            // Loading state
            //
            println("Loading")
        } else if (uiState.value.isError) {
            // Error state
            println("Error")
            Text("An error occurred")
        } else {
            // Success state
            println("Success")
            uiState.value.recipe?.let { recipe ->
                println(recipe.title)
                DetailComponent(paddingValues, recipe)
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


