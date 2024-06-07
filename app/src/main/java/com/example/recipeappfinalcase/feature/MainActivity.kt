package com.example.recipeappfinalcase.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recipeappfinalcase.feature.home.HomeScreen
import com.example.recipeappfinalcase.feature.ui.theme.RecipeAppFinalCaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeAppFinalCaseTheme {
                HomeScreen {
                    
                }
            }
        }
    }
}
