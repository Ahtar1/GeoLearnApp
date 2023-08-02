package com.example.geolearnapp.domain.use_case

import com.example.geolearnapp.domain.repository.CountryRepository

class GetCountry(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(id: Int) = countryRepository.getCountry(id)
}