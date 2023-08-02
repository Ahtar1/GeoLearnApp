package com.example.geolearnapp.data.repository

import com.example.geolearnapp.data.api.CountryApi
import com.example.geolearnapp.data.database.dao.CountryDao
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.data.database.entity.HighScore
import com.example.geolearnapp.domain.repository.CountryRepository

class CountryRepositoryImpl(
    private val countryApi: CountryApi,
    private val countryDao: CountryDao
): CountryRepository{

    override suspend fun getCountries(): List<Country> {
        return countryApi.getCountries().toCountryList()
    }
    override fun getCountriesFromDB(): List<Country> {
        return countryDao.getAllCountries()
    }
    override suspend fun insertCountries(countries: List<Country>) {
        countryDao.insertAll(*countries.toTypedArray())
    }

    override suspend fun getCountry(countryId: Int): Country {
        return countryDao.getCountry(countryId)
    }

    override suspend fun getHighScore(highScoreName: String): Int {
        return countryDao.getHighScore(highScoreName)?.score ?: 0
    }

    override suspend fun insertHighScore(highScoreName: String, highScore: Int) {
        countryDao.insertHighScore(HighScore(highScoreName, highScore))
    }
}