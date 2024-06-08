package com.example.recipeappfinalcase.data.model.detail


import com.google.gson.annotations.SerializedName

data class CaloricBreakdown(
    @SerializedName("percentCarbs")
    val percentCarbs: Double,
    @SerializedName("percentFat")
    val percentFat: Double,
    @SerializedName("percentProtein")
    val percentProtein: Double
)