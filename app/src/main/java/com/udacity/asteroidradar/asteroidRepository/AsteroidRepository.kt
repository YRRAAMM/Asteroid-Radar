package com.udacity.asteroidradar.asteroidRepository

import com.udacity.asteroidradar.asteroidRepository.api.APIBuilder.Network
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import com.udacity.asteroidradar.asteroidRepository.database.DateManager
import com.udacity.asteroidradar.models.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

// Repository for fetching data from the network and store them  on disk.
// api will be split into two parts one to lead the data from the api
// and one to refresh the data from the api

class AsteroidRepository(private val database: AsteroidDatabase) {



    suspend fun deleteAsteroids() {
        withContext(Dispatchers.IO) {
            // getting the data of today and 
            database.databaseDao.deleteAsteroids(DateManager.getToday())
        }
    }

    suspend fun refreshAsteroidsItems() {
        withContext(Dispatchers.IO) {
            // waiting to make the api.
            var asteroidList: ArrayList<Asteroid>
            withContext(Dispatchers.IO) {
//                val asteroidResponseBody: ResponseBody =
            }
        }
    }


}
