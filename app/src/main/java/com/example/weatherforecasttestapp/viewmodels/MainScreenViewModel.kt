package com.example.weatherforecasttestapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherforecasttestapp.model.entities.CurrentWeatherApiResponse
import com.example.weatherforecasttestapp.model.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _currentWeather: MutableLiveData<CurrentWeatherApiResponse> = MutableLiveData()
    val currentWeather: LiveData<CurrentWeatherApiResponse> = _currentWeather

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> = _error

    fun getCurrentWeather(apiKey: String, city: String) {
        val disposable = repository.getCurrentWeather(apiKey, city)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _currentWeather.value = it
            }, {
                _error.value = it
            })
    }

}