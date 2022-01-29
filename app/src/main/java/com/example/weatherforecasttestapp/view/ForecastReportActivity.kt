package com.example.weatherforecasttestapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.weatherforecasttestapp.R
import com.example.weatherforecasttestapp.databinding.ActivityForecastReportBinding
import com.example.weatherforecasttestapp.utils.Constants
import com.example.weatherforecasttestapp.view.adapters.DailyForecastAdapter
import com.example.weatherforecasttestapp.view.adapters.HourlyForecastAdapter
import com.example.weatherforecasttestapp.viewmodels.ForecastReportViewModel
import com.example.weatherforecasttestapp.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForecastReportBinding

    private val viewModel by viewModels<ForecastReportViewModel>()

    private var hourlyForecastAdapter = HourlyForecastAdapter(this)

    private var dailyForecastAdapter = DailyForecastAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        setAdapters()
        setObservers()

        val intent = intent
        val city = intent.getStringExtra("city")
        if (city != null) {
            viewModel.getForecast(Constants.apiKey, city, Constants.numberOfDaysForForecast)
        }

    }

    private fun setListeners() {
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.settings -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setAdapters() {
        binding.rvHourlyForecast.adapter = hourlyForecastAdapter
        binding.rvDailyForecast.adapter = dailyForecastAdapter
    }

    private fun setObservers() {
        viewModel.forecast.observe(this) {
            //Date setup
            val delimiter1 = " "
            val delimiter2 = "-"
            val dateArray = it.location.localtime.split(delimiter1, delimiter2)
            val date = "${Constants.monthsTextFormatShort[dateArray[1]]} ${dateArray[2]}"
            binding.forecastTodayDate.text = date
            //Hourly forecast
            hourlyForecastAdapter.updateData(it.forecast.forecastday[0].hour)
            //Daily forecast
            dailyForecastAdapter.updateData(it.forecast.forecastday)
        }

        viewModel.error.observe(this) {
            //binding.tvForecast.text = "err"
//            AlertDialog.Builder(this).setMessage(R.string.curr_weather_error)
//                .setPositiveButton(R.string.retry) { _, _ ->
//                    //TODO
//                }
//                .setNegativeButton(R.string.close) { dialog, _ ->
//                    dialog.dismiss()
//                }
//                .show()
        }
    }
}