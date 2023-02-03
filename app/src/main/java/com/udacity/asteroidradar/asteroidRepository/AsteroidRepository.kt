package com.udacity.asteroidradar.asteroidRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.asteroidRepository.api.APIBuilder.Network
import com.udacity.asteroidradar.asteroidRepository.api.asDomainAsteroid

import com.udacity.asteroidradar.asteroidRepository.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.asteroidRepository.database.Asteroid
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import com.udacity.asteroidradar.asteroidRepository.database.asDomainModel
import com.udacity.asteroidradar.models.Ast_domain
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.utils.DateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

// Repository for fetching data from the network and store them  on disk.
// api will be split into two parts one to lead the data from the api
// and one to refresh the data from the api

class AsteroidRepository(private val database: AsteroidDatabase) {

    private val retrofit = Network

    val asteroids: LiveData<List<Ast_domain>> =
        Transformations.map(database.databaseDao.getAllAsteroids()) {
            it.asDomainModel()
        }

    val thisWeek: LiveData<List<Ast_domain>> =
        Transformations.map(database.databaseDao.getThisWeekAsteroids(DateManager.getToday(), DateManager.getSevenDaysLater())) {
            it.asDomainModel()
        }

    val today: LiveData<List<Ast_domain>> =
        Transformations.map(database.databaseDao.getThisDayAsteroids(DateManager.getToday())) {
            it.asDomainModel()
        }
    // handle the data that will be stored inside the database from the network.

    suspend fun deleteAsteroids() {
        withContext(Dispatchers.IO) {
            // getting the data of today and
            database.databaseDao.deleteAsteroids(DateManager.getToday())
        }
    }

    suspend fun refreshAsteroidsItems() {
        var asteroidList: ArrayList<Asteroid>
        withContext(Dispatchers.IO) {
            // waiting to make the api.

            val asteroidResponseBody: String = retrofit.retrofitService.getAsteroids()

            asteroidList = parseAsteroidsJsonResult(JSONObject(asteroidResponseBody))

            database.databaseDao.insertAsteroids(*asteroidList.asDomainAsteroid())
        }
    }

    suspend fun getNetworkPicture(): PictureOfDay? {
        var picture: PictureOfDay

        withContext(Dispatchers.IO) {
            picture = retrofit.retrofitService.getPictureOfDay()
        }
        if (picture.mediaType == "image") {
            return picture
        } else return null
    }

}
