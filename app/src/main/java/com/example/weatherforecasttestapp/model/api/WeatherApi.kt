package com.example.weatherforecasttestapp.model.api

import com.example.weatherforecasttestapp.model.entities.CurrentWeatherApiResponse
import com.example.weatherforecasttestapp.model.entities.ForecastApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @Headers("Content-Type: application/json")
    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
    ): Single<CurrentWeatherApiResponse>

    @Headers("Content-Type: application/json")
    @GET("forecast.json")
    fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: String
    ): Single<ForecastApiResponse>
}