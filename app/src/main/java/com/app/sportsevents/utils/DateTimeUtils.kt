package com.app.sportsevents.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

fun getDateObject(timeMillis: Long): DateObject {
    val date = Date(timeMillis)
    val calendar = Calendar.getInstance()
    calendar.time = date

    val dayOfTheMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)

    return DateObject(
        getDayOfWeek(timeMillis),
        dayOfTheMonth,
        getMonthOfYear(month),
        year,
        getTime(timeMillis)
    )
}

data class DateObject(
    var dayOfWeek: String,
    var dayOfTheMonth: Int,
    var month: String,
    var year: Int,
    var time: String
)

fun getDayOfWeek(timeMillis: Long): String {
    val calendar = Calendar.getInstance()
    calendar.time = Date(timeMillis)
    val formatter = SimpleDateFormat("EEEE")
    return formatter.format(calendar.time)
}

fun getTime(timeMillis: Long): String {
    val dateFormat = SimpleDateFormat("HH:mm a")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(Date(timeMillis))
}

fun getMonthOfYear(representation: Int): String {
    return when (representation) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> ""
    }
}