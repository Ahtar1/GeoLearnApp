package com.example.geolearnapp.domain.use_case

import com.example.geolearnapp.domain.repository.CountryRepository

class GetCountries(
    private val countriesRepository: CountryRepository
) {
    suspend operator fun invoke() = countriesRepository.getCountries()
}