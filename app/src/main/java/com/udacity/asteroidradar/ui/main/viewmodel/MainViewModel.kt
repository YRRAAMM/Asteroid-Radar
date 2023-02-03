package com.udacity.asteroidradar.ui.main.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.asteroidRepository.AsteroidRepository
import com.udacity.asteroidradar.asteroidRepository.database.Asteroid
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import com.udacity.asteroidradar.models.Ast_domain
import com.udacity.asteroidradar.models.PictureOfDay

import kotlinx.coroutines.launch
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue

class MainViewModel(application: Application) : ViewModel() {

    private val database = AsteroidDatabase.getInstance(application)
    private val repository = AsteroidRepository(database)



    val asteroid = repository.asteroids

    private val _weekAsteroid = MutableLiveData<List<Ast_domain>>()
    val weekAsteroid = repository.thisWeek

    private val _todayAsteroid = MutableLiveData<Ast_domain>()
    val todayAsteroid = repository.today

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

    private suspend fun allAsteroids(): LiveData<List<Ast_domain>> {
        TODO("Not implemented yet")
    }

    private suspend fun weekAsteroid() {
        TODO("Not implemented yet")

    }

    private suspend fun todayAsteroid() {
        TODO("Not implemented yet")
    }


}