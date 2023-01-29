package com.udacity.asteroidradar.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.models.Asteroid

class MainViewModel : ViewModel() {
    val asteroids = MutableLiveData<List<Asteroid>>()
}