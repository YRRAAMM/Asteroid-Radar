package com.udacity.asteroidradar.asteroidRepository.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.models.Asteroid

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM database_asteroids WHERE closeApproachDate >= :startDate AND closeApproachDate <= :endDate ORDER BY closeApproachDate")
    fun getThisWeekAsteroids(startDate: String, endDate: String): LiveData<List<Asteroid>>

    @Query("SELECT * FROM database_asteroids ORDER BY closeApproachDate")
    fun getAllAsteroids(): LiveData<List<Asteroid>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroid(vararg asteroid: Asteroid)

    @Query("DELETE FROM database_asteroids WHERE closeApproachDate < :today")
    fun deleteAsteroids(today: String): Int

}