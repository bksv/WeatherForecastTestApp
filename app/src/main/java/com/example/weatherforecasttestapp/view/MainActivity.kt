package com.example.weatherforecasttestapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.weatherforecasttestapp.R
import com.example.weatherforecasttestapp.databinding.ActivityMainBinding
import com.example.weatherforecasttestapp.utils.Constants
import com.example.weatherforecasttestapp.utils.Globals
import com.example.weatherforecasttestapp.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setCitiesList()
        setListeners()
        setObservers()

        viewModel.getCurrentWeather(Constants.apiKey, binding.autoCompleteTextView.text.toString())
    }

    private fun setCitiesList() {
        val cities = resources.getStringArray(R.array.cities)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, cities)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.setText(cities[0], false)
    }

    private fun setListeners() {
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.getCurrentWeather(
                Constants.apiKey,
                binding.autoCompleteTextView.text.toString()
            )
        }

        binding.btnMainForecastReport.setOnClickListener {
            val intent = Intent(this, ForecastReportActivity::class.java)
            intent.putExtra("city", binding.autoCompleteTextView.text.toString())
            startActivity(intent)
        }
    }

    private fun setObservers() {
        viewModel.currentWeather.observe(this) {

            //Picture setup
            val iconName =
                Globals.getLocalWeatherIconName(it.current.is_day, it.current.condition.code)
            val icon = resources.getIdentifier(iconName, "drawable", packageName)
            Glide.with(this)
                .load(icon)
                .into(binding.ivMainWeather)

            //Date setup
            val delimiter1 = " "
            val delimiter2 = "-"
            val dateArray = it.location.localtime.split(delimiter1, delimiter2)
            val date =
                "${resources.getString(R.string.today_main)} ${dateArray[2]} ${Constants.monthsTextFormat[dateArray[1]]}"
            binding.tvMainDate.text = date

            //Temp
            binding.tvMainTemperature.text = " ${it.current.temp_c.toInt()}°"

            //Weather description
            binding.tvMainWeatherTextDescr.text = it.current.condition.text

            //Wind and humidity
            binding.tvMainWindValue.text = "${it.current.wind_kph.toInt()} km/h"
            binding.tvMainHumidityValue.text = "${it.current.humidity} %"
        }

        viewModel.error.observe(this) {
            AlertDialog.Builder(this).setMessage(R.string.curr_weather_error)
                .setPositiveButton(R.string.retry) { _, _ ->
                    viewModel.getCurrentWeather(
                        Constants.apiKey,
                        binding.autoCompleteTextView.text.toString()
                    )
                }
                .setNegativeButton(R.string.close) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

}