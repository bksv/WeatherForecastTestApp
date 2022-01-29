package com.example.weatherforecasttestapp.utils

import com.bumptech.glide.Glide

object Globals {

    //Gets a local icon name of weather based on day/night and condition code from Api response
    fun getLocalWeatherIconName(isDay: Int, conditionCode: Int): String {
        return if (isDay == 1) {
            val imageName =
                "ic_day_" + Constants.codesToImagesMap[conditionCode.toString()]
            imageName
        } else {
            val imageName =
                "ic_night_" + Constants.codesToImagesMap[conditionCode.toString()]
            imageName
        }
    }

}