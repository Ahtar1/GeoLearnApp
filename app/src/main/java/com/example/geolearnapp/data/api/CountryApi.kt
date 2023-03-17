package com.example.geolearnapp.data.api

import com.example.geolearnapp.data.api.model.Countries
import retrofit2.http.GET

interface CountryApi {

    @GET("countries/capital")
    suspend fun getCountries(): Countries
}