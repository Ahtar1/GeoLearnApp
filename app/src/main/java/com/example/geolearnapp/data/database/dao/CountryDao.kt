package com.example.geolearnapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.geolearnapp.data.database.entity.Country
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
}