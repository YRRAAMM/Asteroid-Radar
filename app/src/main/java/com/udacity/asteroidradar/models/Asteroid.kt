package com.udacity.asteroidradar.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "database_asteroids")
@Parcelize
data class Asteroid(

    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val codename: String,
    @ColumnInfo(name = "closeApproachDate") val closeApproachDate: String,
    @ColumnInfo(name = "absoluteMagnitude") val absoluteMagnitude: Double,
    @ColumnInfo(name = "estimatedDiameter") val estimatedDiameter: Double,
    @ColumnInfo(name = "relativeVelocity") val relativeVelocity: Double,
    @ColumnInfo(name = "distanceFromEarth") val distanceFromEarth: Double,
    @ColumnInfo(name = "isPotentiallyHazardous") val isPotentiallyHazardous: Boolean

    ) : Parcelable