package com.example.geolearnapp.data.repository

import com.example.geolearnapp.data.api.CountryApi
import com.example.geolearnapp.data.database.dao.CountryDao
import com.example.geolearnapp.data.database.entity.Countries
import com.example.geolearnapp.data.database.entity.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val countryApi: CountryApi,
    private val countryDao: CountryDao
) {
    suspend fun getCountries(): List<Country> {
       return countryApi.getCountries().toCountryList()
    }
    fun getCountriesFromDB(): List<Country> {
        return countryDao.getAllCountries()
    }
    suspend fun insertCountries(countries: List<Country>) {
        countryDao.insertAll(*countries.toTypedArray())
    }
}