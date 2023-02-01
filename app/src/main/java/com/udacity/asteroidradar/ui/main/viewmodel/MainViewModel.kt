package com.udacity.asteroidradar.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.asteroidRepository.AsteroidRepository
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import com.udacity.asteroidradar.models.PictureOfDay

import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {

    private val database = AsteroidDatabase.getInstance(application)
    private val repository = AsteroidRepository(database)

    val asteroid = repository.asteroids

    private val _picture = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _picture

    init {

        viewModelScope.launch {
            repository.refreshAsteroidsItems()
            refreshPhoto()
        }
    }


    private suspend fun refreshPhoto() {
        _picture.value = repository.getNetworkPicture()
    }
}