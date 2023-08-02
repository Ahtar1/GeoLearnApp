package com.example.geolearnapp.domain.use_case

import com.example.geolearnapp.data.database.entity.HighScore
import com.example.geolearnapp.domain.repository.CountryRepository

class InsertHighScore(
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(highScoreName: String, highScore: Int) = countryRepository.insertHighScore(highScoreName, highScore)
}