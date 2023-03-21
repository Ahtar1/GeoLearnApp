package com.example.geolearnapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.geolearnapp.data.database.dao.CountryDao
import com.example.geolearnapp.data.database.entity.Country
import com.example.geolearnapp.data.database.entity.HighScore

@Database(
    entities = [Country::class, HighScore::class],
    version = 2
)
 abstract class CountryDatabase: RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object{
        val migration1To2= object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `high_scores` (`name` TEXT PRIMARY KEY NOT NULL, `score` INTEGER NOT NULL)")
            }
        }
    }
}