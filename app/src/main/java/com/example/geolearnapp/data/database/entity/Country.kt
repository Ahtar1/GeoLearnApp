package com.example.geolearnapp.data.database.entity


import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "countries")
@JsonClass(generateAdapter = true)
data class Country(
    @ColumnInfo(name = "capital")
    @Json(name = "capital")
    val capital: String,
    @ColumnInfo(name = "iso2")
    @Json(name = "iso2")
    val iso2: String,
    @ColumnInfo(name = "iso3")
    @Json(name = "iso3")
    val iso3: String,
    @ColumnInfo(name = "name")
    @Json(name = "name")
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid:Int = 0
}
