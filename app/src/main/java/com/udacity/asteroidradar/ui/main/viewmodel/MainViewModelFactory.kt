package com.udacity.asteroidradar.ui.main.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val application: Application): ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}