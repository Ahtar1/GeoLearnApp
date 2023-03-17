package com.example.geolearnapp.data.repository

import com.example.geolearnapp.data.api.CountryApi
import com.example.geolearnapp.data.api.model.Data
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val countryApi: CountryApi
) {
    suspend fun getCountries(): List<Data> {
       return countryApi.getCountries().data
    }
}