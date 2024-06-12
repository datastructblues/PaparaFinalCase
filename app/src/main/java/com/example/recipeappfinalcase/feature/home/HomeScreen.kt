package com.example.recipeappfinalcase.feature.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeappfinalcase.R
import com.example.recipeappfinalcase.feature.ui.components.BottomSheet
import com.example.recipeappfinalcase.feature.ui.components.AnimatedContent
import com.example.recipeappfinalcase.feature.ui.components.Searchbar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeVM = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsState()
    val query = viewModel.query.collectAsState()

    val showBottomSheet = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchRecipes(query.value)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Home")
                Searchbar(query = query, viewModel = viewModel)
            },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.value.isLoading) {
                AnimatedContent(resource = R.raw.loading)
            } 
            if(state.value.isError){
                Image(painter = painterResource(id = R.drawable.wifi), contentDescription = null, alpha = 0.5f)
                Text("No Internet Connection", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            } else {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Recipes",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(16.dp, 0.dp, 0.dp, 0.dp)
                            .weight(1f)
                    )
                    IconButton(
                        modifier = Modifier
                            .padding(8.dp,0.dp),
                        onClick = {
                            showBottomSheet.value = true
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.tune),
                                contentDescription = "Filter"
                            )
                        }
                    )
                }
                ListingComponent(
                    recipes = state.value.recipes?.recipeList ?: emptyList(),
                ) { id ->
                    navController.navigate("detail/$id")
                }
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
    }
}

