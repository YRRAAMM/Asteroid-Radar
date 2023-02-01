package com.udacity.asteroidradar.asteroidRepository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.utils.Constants.DATABASE_NAME


@Database(entities = [Asteroid::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase: RoomDatabase() {

    abstract val databaseDao: DatabaseDao

    companion object {

        @Volatile
        private lateinit var INSTANCE: AsteroidDatabase

        fun getInstance(context: Context): AsteroidDatabase {

            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        DATABASE_NAME
                    ) .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE
                }
                return INSTANCE
            }
        }
    }

}