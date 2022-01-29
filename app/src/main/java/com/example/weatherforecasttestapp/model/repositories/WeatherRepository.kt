package com.example.weatherforecasttestapp.model.repositories

import com.example.weatherforecasttestapp.model.api.WeatherApi
import com.example.weatherforecasttestapp.model.entities.CurrentWeatherApiResponse
import com.example.weatherforecasttestapp.model.entities.ForecastApiResponse
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: WeatherApi
) {
    fun getCurrentWeather(apiKey: String, city: String): Single<CurrentWeatherApiResponse> =
        api.getCurrentWeather(apiKey, city)

    fun getForecast(apiKey: String, city: String, days: String): Single<ForecastApiResponse> =
        api.getForecast(apiKey, city, days)
}