package com.udacity.asteroidradar.asteroidRepository.database

import android.annotation.SuppressLint
import com.udacity.asteroidradar.utils.Constants
import java.text.SimpleDateFormat
import java.util.*


// todo fix before sending
object DateManager {

    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    fun getToday():String{
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    @SuppressLint("WeekBasedYear")
    fun getSevenDaysLater(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, Constants.DEFAULT_END_DATE_DAYS)
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
        return dateFormat.format(currentTime)
    }
}