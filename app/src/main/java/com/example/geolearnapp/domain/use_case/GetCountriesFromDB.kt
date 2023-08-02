package com.example.geolearnapp.domain.use_case

import com.example.geolearnapp.domain.repository.CountryRepository

class GetCountriesFromDB(
    private val countryRepository: CountryRepository
) {
    operator fun invoke() = countryRepository.getCountriesFromDB()
}