package com.udacity.asteroidradar.asteroidRepository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.asteroidRepository.api.APIBuilder.Network
import com.udacity.asteroidradar.asteroidRepository.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import com.udacity.asteroidradar.asteroidRepository.database.DatabaseDao
import com.udacity.asteroidradar.asteroidRepository.database.DateManager
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

// Repository for fetching data from the network and store them  on disk.
// api will be split into two parts one to lead the data from the api
// and one to refresh the data from the api

class AsteroidRepository(private val database: AsteroidDatabase) {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofit = Network.create(Constants.BASE_URL, moshi)

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
                val asteroidResponseBody: Response<ResponseBody> = retrofit.retrofitService.getAsteroids(
                    DateManager.getToday(), DateManager.getSevenDaysLater(), Constants.API_KEY)

                asteroidList = parseAsteroidsJsonResult(JSONObject(asteroidResponseBody.toString()))
                database.databaseDao.insertAsteroid(asteroidList)
            }
        }
    }


}
