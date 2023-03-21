package com.example.geolearnapp.data.database.dao

import androidx.room.*
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.data.database.entity.HighScore
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg country: Country): List<Long>

    @Query("SELECT * FROM countries")
    fun getAllCountries(): List<Country>

    @Query("SELECT * FROM countries WHERE uid = :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM countries")
    suspend fun deleteAllCountries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHighScore(highScore: HighScore?)

    @Query("SELECT * FROM high_scores WHERE name = :highScoreName")
    suspend fun getHighScore(highScoreName: String): HighScore?
}