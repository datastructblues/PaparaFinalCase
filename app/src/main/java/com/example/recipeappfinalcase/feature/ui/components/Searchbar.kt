package com.example.recipeappfinalcase.feature.ui.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.recipeappfinalcase.R
import com.example.recipeappfinalcase.feature.home.HomeVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchbar(
    query: State<String>,
    modifier: Modifier = Modifier,
    viewModel: HomeVM,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = query.value,
            onValueChange = { viewModel.query.value = it },
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyLarge,
            label = { Text("Search Recipes") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(8.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))
        val showBottomSheet = remember { mutableStateOf(false) }
        if (showBottomSheet.value) {
            BottomSheet(
                onDismissRequest = {
                    showBottomSheet.value = false
                },
                viewModel = viewModel
            )
        }
    }
}
