package com.udacity.asteroidradar.asteroidRepository.api


import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.utils.Constants.API_KEY
import retrofit2.http.GET

interface APIService{

    @GET("neo/rest/v1/feed?api_key=$API_KEY")
    suspend fun getAsteroids(): String

    @GET("planetary/apod?api_key=$API_KEY")
    suspend fun getPictureOfDay(): PictureOfDay

}

