package com.example.geolearnapp.di

import com.example.geolearnapp.data.api.ApiConstants
import com.example.geolearnapp.data.api.CountryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryApiModule {

    @Provides
    @Singleton
    fun provideCountryApi(builder: Retrofit.Builder): CountryApi {
        return builder
            .build()
            .create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }

}