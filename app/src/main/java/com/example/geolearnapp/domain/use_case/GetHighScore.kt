package com.example.geolearnapp.domain.use_case

import com.example.geolearnapp.domain.repository.CountryRepository

class GetHighScore(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(highScoreName: String) = countryRepository.getHighScore(highScoreName)
}