package com.udacity.asteroidradar.asteroidRepository.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.models.Ast_domain
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "database_asteroids")
data class Asteroid(

    @PrimaryKey val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean ) : Parcelable

fun List<Asteroid>.asDomainModel(): List<Ast_domain> {
    return map {
        Ast_domain(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )

    }
}

