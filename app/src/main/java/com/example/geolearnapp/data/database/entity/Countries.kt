package com.example.geolearnapp.data.database.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Countries(
    @Json(name = "data")
    val `data`: List<Country>,
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "msg")
    val msg: String
){
    fun toCountryList(): List<Country> {
        return `data`
    }
}