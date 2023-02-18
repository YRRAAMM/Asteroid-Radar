package com.udacity.asteroidradar.ui.main.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.asteroidRepository.AsteroidRepository
import com.udacity.asteroidradar.asteroidRepository.database.Asteroid
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import com.udacity.asteroidradar.models.PictureOfDay
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@RequiresApi(Build.VERSION_CODES.O)

class MainViewModel(application: Application) : ViewModel() {

    private val database = AsteroidDatabase.getInstance(application)
    private val repository = AsteroidRepository(database)

    private val _asteroid = MutableLiveData<List<Asteroid>>()
    val asteroid : LiveData<List<Asteroid>>
        get() = _asteroid

    private val _thisWeekAsteroid = MutableLiveData<List<Asteroid>>()
    val thisWeekAsteroid : LiveData<List<Asteroid>>
        get() = _thisWeekAsteroid

    private val _todayAsteroid = MutableLiveData<List<Asteroid>>()
    val todayAsteroid : LiveData<List<Asteroid>>
        get() = _todayAsteroid

    private val _picture = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _picture

    init {
        todayAsteroids()
        weekAsteroids()
        asteroids()
        refreshPhoto()

    }
    private fun weekAsteroids() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _thisWeekAsteroid.postValue(repository.thisWeek())
            }
        }
    }

    private fun todayAsteroids() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _todayAsteroid.postValue(repository.today())
            }
        }
    }

    private fun asteroids() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _asteroid.postValue(repository.asteroids())
            }
        }
    }

    private fun refreshPhoto() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _picture.postValue(repository.getNetworkPicture())
            }
        }
    }


}