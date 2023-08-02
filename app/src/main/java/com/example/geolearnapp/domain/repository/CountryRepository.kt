package com.example.geolearnapp.domain.repository

import com.example.geolearnapp.data.api.CountryApi
import com.example.geolearnapp.data.database.dao.CountryDao
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.data.database.entity.HighScore
import javax.inject.Inject

interface CountryRepository{

    suspend fun getCountries(): List<Country>

    fun getCountriesFromDB(): List<Country>

    suspend fun insertCountries(countries: List<Country>)

    suspend fun getCountry(countryId: Int): Country

    suspend fun getHighScore(highScoreName: String): Int

    suspend fun insertHighScore(highScoreName: String, highScore: Int)

    /*
    suspend fun getCountries(): List<Country> {
       return countryApi.getCountries().toCountryList()
    }
    fun getCountriesFromDB(): List<Country> {
        return countryDao.getAllCountries()
    }
    suspend fun insertCountries(countries: List<Country>) {
        countryDao.insertAll(*countries.toTypedArray())
    }

    suspend fun getCountry(countryId: Int): Country {
        return countryDao.getCountry(countryId)
    }

    suspend fun getHighScore(highScoreName: String): Int {
        return countryDao.getHighScore(highScoreName)?.score ?: 0
    }

    suspend fun insertHighScore(highScoreName: String, highScore: Int) {
        countryDao.insertHighScore(HighScore(highScoreName, highScore))
    }

     */
}