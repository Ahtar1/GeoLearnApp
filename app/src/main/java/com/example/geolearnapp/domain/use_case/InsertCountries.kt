package com.example.geolearnapp.domain.use_case

import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.domain.repository.CountryRepository

class InsertCountries(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(countries: List<Country>) = countryRepository.insertCountries(countries)
}