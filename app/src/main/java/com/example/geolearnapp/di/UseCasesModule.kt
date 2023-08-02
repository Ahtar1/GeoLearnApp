package com.example.geolearnapp.di

import com.example.geolearnapp.data.api.CountryApi
import com.example.geolearnapp.data.database.CountryDatabase
import com.example.geolearnapp.data.database.dao.CountryDao
import com.example.geolearnapp.data.repository.CountryRepositoryImpl
import com.example.geolearnapp.domain.repository.CountryRepository
import com.example.geolearnapp.domain.use_case.CountryUseCases
import com.example.geolearnapp.domain.use_case.GetCountries
import com.example.geolearnapp.domain.use_case.GetCountriesFromDB
import com.example.geolearnapp.domain.use_case.GetCountry
import com.example.geolearnapp.domain.use_case.GetHighScore
import com.example.geolearnapp.domain.use_case.InsertCountries
import com.example.geolearnapp.domain.use_case.InsertHighScore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideCountryRepository(db: CountryDatabase,api: CountryApi): CountryRepository {
        return CountryRepositoryImpl(api,db.countryDao())
    }

    @Provides
    @Singleton
    fun provideCountryUseCases(repository: CountryRepository): CountryUseCases {
        return CountryUseCases(
            getCountry = GetCountry(repository),
            getCountries = GetCountries(repository),
            getCountriesFromDB = GetCountriesFromDB(repository),
            insertCountries = InsertCountries(repository),
            getHighScore = GetHighScore(repository),
            insertHighScore = InsertHighScore(repository)

        )
    }
}