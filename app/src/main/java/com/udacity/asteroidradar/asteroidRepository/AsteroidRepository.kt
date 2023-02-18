package com.udacity.asteroidradar.asteroidRepository

import android.os.Build
import androidx.annotation.RequiresApi
import com.udacity.asteroidradar.asteroidRepository.api.APIBuilder.Network
import com.udacity.asteroidradar.asteroidRepository.api.asDomainAsteroid

import com.udacity.asteroidradar.asteroidRepository.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.asteroidRepository.database.Asteroid
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.utils.DateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

// Repository for fetching data from the network and store them  on disk.
// api will be split into two parts one to lead the data from the api
// and one to refresh the data from the api

@RequiresApi(Build.VERSION_CODES.O)
class AsteroidRepository(private val database: AsteroidDatabase) {

    private val retrofit = Network.retrofitService


    fun asteroids() = database.databaseDao.getAllAsteroids()


    fun thisWeek() = database.databaseDao.getThisWeekAsteroids(
                DateManager.getToday(),
                DateManager.getSevenDaysLater()
            )


    fun today() = database.databaseDao.getThisDayAsteroids(DateManager.getToday())




    fun deleteAsteroids() {
        database.databaseDao.deleteAsteroids(DateManager.getToday())
    }

    suspend fun refreshAsteroidsItems() {
        var asteroidList: ArrayList<Asteroid>
        withContext(Dispatchers.IO) {
            // waiting to make the api.
            val asteroidResponseBody: String = retrofit.getAsteroids()

            asteroidList = parseAsteroidsJsonResult(JSONObject(asteroidResponseBody))

            database.databaseDao.insertAsteroids(*asteroidList.asDomainAsteroid())
        }
    }

    suspend fun getNetworkPicture(): PictureOfDay? {
        var picture: PictureOfDay

        withContext(Dispatchers.IO) {
            picture = retrofit.getPictureOfDay()
        }
        if (picture.mediaType == "image") {
            return picture
        } else return null
    }

}
