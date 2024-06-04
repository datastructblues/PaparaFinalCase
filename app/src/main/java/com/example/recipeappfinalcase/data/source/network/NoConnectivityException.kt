package com.example.recipeappfinalcase.data.source.network

import java.io.IOException


class NoConnectivityException : IOException() {
    override val message: String
        get() = "No network connection."
}