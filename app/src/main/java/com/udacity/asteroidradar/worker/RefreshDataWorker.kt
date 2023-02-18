package com.udacity.asteroidradar.worker

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.asteroidRepository.AsteroidRepository
import com.udacity.asteroidradar.asteroidRepository.database.AsteroidDatabase
import retrofit2.HttpException

@RequiresApi(Build.VERSION_CODES.O)
class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params){

    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.getInstance(applicationContext)
        val repository = AsteroidRepository(database)

        return try {
            repository.deleteAsteroids()
            repository.refreshAsteroidsItems()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}
