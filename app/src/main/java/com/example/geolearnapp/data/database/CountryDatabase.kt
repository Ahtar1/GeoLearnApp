package com.example.geolearnapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.geolearnapp.data.database.dao.CountryDao
import com.example.geolearnapp.data.database.entity.Country

@Database(entities = [Country::class], version = 1)
 abstract class CountryDatabase: RoomDatabase() {

    abstract fun countryDao(): CountryDao
}