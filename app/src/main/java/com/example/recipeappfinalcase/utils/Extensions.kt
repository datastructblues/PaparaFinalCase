package com.example.recipeappfinalcase.utils

import android.text.Html
import androidx.compose.foundation.ScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.data.source.local.FavoriteRecipe

fun Modifier.parallaxLayoutModifier(scrollState: ScrollState, rate: Int) =
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val height = if (rate > 0) scrollState.value / rate else scrollState.value
        layout(placeable.width, placeable.height) {
            placeable.place(0, height)
        }
    }

fun String.parseToText(): String {
    val textWithoutHtml = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()

    val formattedText = StringBuilder()
    textWithoutHtml.split("\n").forEach { line ->
        val formattedLine = if (line.startsWith("*")) {
            "<b>$line</b>"
        } else {
            line
        }
        formattedText.appendLine(formattedLine)
    }

    return formattedText.toString()
}

fun DetailResponse.toFavoriteRecipe(): FavoriteRecipe {
    return FavoriteRecipe(
        id = id,
        title = title,
        image = image,
        summary = summary,
        servings = servings,
        readyInMinutes = readyInMinutes,
        instructions = instructions,
        healthScore = healthScore
    )
}

fun FavoriteRecipe.toDetailResponse(): DetailResponse {
    return DetailResponse(
        id = id,
        title = title,
        image = image,
        summary = summary,
        instructions = instructions,
        readyInMinutes = readyInMinutes,
        servings = servings,
        healthScore = healthScore
    )
}