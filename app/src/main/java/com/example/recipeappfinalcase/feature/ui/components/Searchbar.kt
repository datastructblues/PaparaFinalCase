package com.example.recipeappfinalcase.feature.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipeappfinalcase.feature.home.HomeVM


@Composable
fun Searchbar(
    query: State<String>,
    modifier: Modifier = Modifier,
    viewModel: HomeVM,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = query.value,
            onValueChange = { viewModel.query.value = it },
            maxLines = 1,
            label = { Text("Search Recipes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
