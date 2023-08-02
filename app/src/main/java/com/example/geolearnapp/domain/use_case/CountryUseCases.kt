package com.example.geolearnapp.domain.use_case

data class CountryUseCases(
    val getCountry: GetCountry,
    val getCountries: GetCountries,
    val getCountriesFromDB: GetCountriesFromDB,
    val insertCountries: InsertCountries,
    val getHighScore: GetHighScore,
    val insertHighScore: InsertHighScore
)
