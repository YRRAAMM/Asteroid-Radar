package com.udacity.asteroidradar.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


// todo fix before sending
object DateManager {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    fun getToday(): String {
        val currentDate = LocalDate.now()
        val dateFormat = DateTimeFormatter.ofPattern(Constants.API_QUERY_DATE_FORMAT)
        return currentDate.format(dateFormat)
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