package com.example.weatherforecasttestapp.model.entities

data class CurrentWeatherApiResponse(
    val current: Current,
    val location: Location
)