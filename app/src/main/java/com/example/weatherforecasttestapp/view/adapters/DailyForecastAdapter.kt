package com.example.weatherforecasttestapp.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecasttestapp.databinding.DailyWeatherItemBinding
import com.example.weatherforecasttestapp.model.entities.Forecastday
import com.example.weatherforecasttestapp.utils.Constants
import com.example.weatherforecasttestapp.utils.Globals

class DailyForecastAdapter(private val activity: Activity) :
    RecyclerView.Adapter<DailyForecastAdapter.DailyWeatherViewHolder>() {

    private var dailyForecast: List<Forecastday> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val binding: DailyWeatherItemBinding =
            DailyWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val dateArray = dailyForecast[position].date.split("-")
        val date = "${Constants.monthsTextFormatShort[dateArray[1]]} ${dateArray[2]}"
        holder.binding.tvDailyWeatherTime.text = date

        val iconName = Globals.getLocalWeatherIconName(
            1, dailyForecast[position].day.condition.code
        )
        val icon = activity.resources.getIdentifier(iconName, "drawable", activity.packageName)
        Glide.with(activity)
            .load(icon)
            .into(holder.binding.ivDailyWeather)

        holder.binding.tvDailyWeatherTemp.text = "${dailyForecast[position].day.avgtemp_c}°C"
    }

    override fun getItemCount(): Int {
        return dailyForecast.size
    }

    fun updateData(forecast: List<Forecastday>) {
        dailyForecast = forecast
        notifyDataSetChanged()
    }

    inner class DailyWeatherViewHolder(var binding: DailyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}