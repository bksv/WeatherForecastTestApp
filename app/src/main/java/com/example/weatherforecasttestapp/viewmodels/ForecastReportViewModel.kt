package com.example.weatherforecasttestapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecasttestapp.model.entities.CurrentWeatherApiResponse
import com.example.weatherforecasttestapp.model.entities.ForecastApiResponse
import com.example.weatherforecasttestapp.model.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ForecastReportViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _forecast: MutableLiveData<ForecastApiResponse> = MutableLiveData()
    val forecast: LiveData<ForecastApiResponse> = _forecast

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error

    fun getForecast(apiKey: String, city: String, days: String) {
        val disposable = repository.getForecast(apiKey, city, days)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _forecast.value = it
            }, {
                _error.value = it
            })
    }

}