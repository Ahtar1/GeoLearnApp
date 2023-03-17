package com.example.geolearnapp.data.api.model


import androidx.compose.ui.graphics.Color
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "capital")
    val capital: String,
    @Json(name = "iso2")
    val iso2: String,
    @Json(name = "iso3")
    val iso3: String,
    @Json(name = "name")
    val name: String
) {
    companion object {
        val colors = listOf(
            Color(0xFFFF0000),
            Color(0xFFFF7F00),
            Color(0xFFFFFF00),
            Color(0xFF00FF00),
            Color(0xFF0000FF),
            Color(0xFF4B0082),
            Color(0xFF9400D3),
        )
    }
}
