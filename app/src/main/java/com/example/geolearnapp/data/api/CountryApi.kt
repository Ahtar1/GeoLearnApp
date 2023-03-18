package com.example.geolearnapp.data.api

import com.example.geolearnapp.data.database.entity.Countries
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CountryApi {

    @GET("countries/capital")
    suspend fun getCountries(): Countries
}