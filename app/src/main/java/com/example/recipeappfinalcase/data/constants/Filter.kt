package com.example.recipeappfinalcase.data.constants

class Filter{
    companion object{
        val cuisines: Map<String, String> = mapOf(
            "African" to "african",
            "American" to "american",
            "Asian" to "asian",
            "British" to "british",
            "Cajun" to "cajun",
            "Caribbean" to "caribbean",
            "Chinese" to "chinese",
            "European" to "european",
            "French" to "french",
            "German" to "german",
            "Greek" to "greek",
            "Indian" to "indian",
            "Irish" to "irish",
            "Italian" to "italian",
            "Japanese" to "japanese",
            "Jewish" to "jewish",
            "Korean" to "korean",
            "Latin American" to "latinamerican",
            "Mediterranean" to "mediterranean",
            "Mexican" to "mexican",
            "Middle Eastern" to "middleeastern",
            "Nordic" to "nordic",
            "Southern" to "southern",
            "Spanish" to "spanish",
            "Thai" to "thai",
            "Vietnamese" to "vietnamese"
        )

        val diets: Map<String, String> = mapOf(
            "Gluten Free" to "gluten free",
            "Ketogenic" to "ketogenic",
            "Vegetarian" to "vegetarian",
            "Lacto-Vegetarian" to "lactovegetarian",
            "Ovo-Vegetarian" to "ovovegetarian",
            "Vegan" to "vegan",
            "Pescetarian" to "pescetarian",
            "Paleo" to "paleo",
            "Primal" to "primal",
            "Whole30" to "whole30"
        )

    }
}