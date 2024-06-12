package com.example.recipeappfinalcase.feature.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeappfinalcase.data.constants.Filter
import com.example.recipeappfinalcase.feature.home.HomeVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    viewModel: HomeVM
) {
    Column(modifier = Modifier.padding(16.dp)) {
        ModalBottomSheet(onDismissRequest = onDismissRequest) {
            FilterScreen(
                viewModel = viewModel,
                modifier = Modifier.fillMaxSize(),
                onFilterApplied = {
                    viewModel.fetchRecipes(query = viewModel.query.value, cuisine = viewModel.cuisine.value?.lowercase(), diet = viewModel.diet.value?.lowercase())
                    onDismissRequest()
                }
            )
        }
    }
}

@Composable
fun FilterScreen(
    viewModel: HomeVM,
    modifier: Modifier = Modifier,
    onFilterApplied: () -> Unit,
) {
    val selectedCuisine = viewModel.cuisine.collectAsState()
    val selectedDiet = viewModel.diet.collectAsState()
    val cuisines = Filter.cuisines.keys
    val diets = Filter.diets.keys.toList()
    val scrollState = rememberScrollState()
    val expanded = remember { mutableStateOf(false) }
    val limit = 2

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text("Search Filter", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(16.dp))

        Text("Cuisines", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
        Spacer(modifier = Modifier.height(8.dp))

        val visibleCuisines = if (expanded.value) cuisines else cuisines.take(limit)
        val rows = visibleCuisines.chunked(2)

        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { category ->
                    Categories(
                        category = category,
                        isSelected = selectedCuisine.value == category,
                        onSelectedCategoryChanged = { selected ->
                            viewModel.cuisine.value = if (selected) category else null
                        }
                    )
                }
            }
        }

        if (cuisines.size > limit) {
            Button(
                onClick = { expanded.value = !expanded.value },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (expanded.value) "Show Less" else "Show More")
            }
        }

        Text("Diets", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold))
        Spacer(modifier = Modifier.height(8.dp))

        val rowsDiets = diets.chunked(2)

        rowsDiets.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { category ->
                    Categories(
                        category = category,
                        isSelected = selectedDiet.value == category, // Use selectedDiet instead of viewModel.diet.value
                        onSelectedCategoryChanged = { selected ->
                            viewModel.diet.value = if (selected) category else null
                        }
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                viewModel.cuisine.value = null
                viewModel.diet.value = null
                onFilterApplied()
            }) {
                Text("Clear")
            }
            Button(onClick = onFilterApplied) {
                Text("Apply")
            }
        }
    }
}

@Composable
fun Categories(
    category: String,
    isSelected: Boolean,
    onSelectedCategoryChanged: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .size(175.dp, 40.dp)
            .clickable { onSelectedCategoryChanged(!isSelected) },
        border = BorderStroke(1.dp, if (isSelected) Color.Black else Color.Gray)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = isSelected,
                onClick = null,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = category,
                maxLines = 1,
                fontSize = 12.sp,
                overflow = TextOverflow.Visible,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
