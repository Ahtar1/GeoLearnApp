package com.example.geolearnapp.di

import android.content.Context
import androidx.room.Room
import com.example.geolearnapp.data.database.CountryDatabase
import com.example.geolearnapp.data.database.dao.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideCountryDatabase(@ApplicationContext context: Context): CountryDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            CountryDatabase::class.java,
            "country_database"
        ).addMigrations(CountryDatabase.migration1To2).build(
        )
    }
    @Provides
    @Singleton
    fun provideCountryDao(db: CountryDatabase):CountryDao = db.countryDao()
}