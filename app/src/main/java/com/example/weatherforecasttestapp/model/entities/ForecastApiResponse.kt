package com.example.weatherforecasttestapp.model.entities

data class ForecastApiResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)