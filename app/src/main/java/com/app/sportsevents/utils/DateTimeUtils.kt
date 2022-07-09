package com.app.sportsevents.utils

import android.annotation.SuppressLint
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import kotlin.math.abs

private fun daysDifference(timeToCompare: Long): Long {
    val startOfToday = DateTime(DateTimeZone.UTC).withTimeAtStartOfDay()
    val startOfTodayMillis = startOfToday.millis
    val difference = timeToCompare - startOfTodayMillis

    return (difference / (1000 * 60 * 60 * 24)) % 365
}

private fun getDayRepresentation(daysFromToday: Long, time: String): String {
    return when {
        daysFromToday == 1L -> "Tomorrow, $time"
        daysFromToday > 1L -> "In $daysFromToday days"
        daysFromToday == -1L -> "Yesterday, $time"
        daysFromToday < -1L -> "${abs(daysFromToday)} days ago"
        else -> "Today, $time"
    }
}

@SuppressLint("SimpleDateFormat")
private fun getTime(timeMillis: Long): String {
    val dateFormat = SimpleDateFormat("HH:mm a")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(Date(timeMillis))
}

@SuppressLint("SimpleDateFormat")
fun dateStringToTimeMillis(date: String): Long {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return format.parse(date)?.time ?: 0
}

fun isDateTomorrow(timeMillis: Long) = daysDifference(timeMillis) == 1L

fun getDateAndTime(date: String): String {
    val timeMillis = dateStringToTimeMillis(date)
    val time = getTime(timeMillis)
    val daysDifference = daysDifference(timeMillis)
    return getDayRepresentation(daysDifference, time)
}