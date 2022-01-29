package com.example.weatherforecasttestapp.view.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecasttestapp.databinding.HourlyWeatherItemBinding
import com.example.weatherforecasttestapp.model.entities.Hour
import com.example.weatherforecasttestapp.utils.Globals

class HourlyForecastAdapter(private val activity: Activity) :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyWeatherViewHolder>() {

    private var hourlyForecast: List<Hour> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val binding: HourlyWeatherItemBinding =
            HourlyWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        holder.binding.tvHourlyWeatherTemp.text = "${hourlyForecast[position].temp_c.toString()}°C"

        val iconName = Globals.getLocalWeatherIconName(
            hourlyForecast[position].is_day,
            hourlyForecast[position].condition.code
        )
        val icon = activity.resources.getIdentifier(iconName, "drawable", activity.packageName)
        Glide.with(activity)
            .load(icon)
            .into(holder.binding.ivHourlyWeather)

        val time = hourlyForecast[position].time.split(" ")
        holder.binding.tvHourlyWeatherTime.text = time[1]
    }

    override fun getItemCount(): Int {
        return hourlyForecast.size
    }

    fun updateData(hours: List<Hour>) {
        hourlyForecast = hours
        notifyDataSetChanged()
    }

    inner class HourlyWeatherViewHolder(var binding: HourlyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}