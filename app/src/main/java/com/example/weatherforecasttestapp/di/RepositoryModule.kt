package com.example.weatherforecasttestapp.di

import com.example.weatherforecasttestapp.model.api.WeatherApi
import com.example.weatherforecasttestapp.model.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository = WeatherRepository(api)
}