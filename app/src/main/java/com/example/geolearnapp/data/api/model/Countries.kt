package com.example.geolearnapp.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Countries(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "msg")
    val msg: String
)