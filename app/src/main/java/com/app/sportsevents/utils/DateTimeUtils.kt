package com.app.sportsevents.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant

private fun getTime(timeMillis: Long): String {
    val minute = (timeMillis / (1000 * 60)) % 60;
    val hour = (timeMillis / (1000 * 60 * 60)) % 24
    return "$hour:$minute"
}

private fun getDateDifference(timeMillis: Long): Long {
    val difference = System.currentTimeMillis() - timeMillis
    return (difference / (1000 * 60 * 60 * 24)) % 365
}

@RequiresApi(Build.VERSION_CODES.O)
fun getFormattedDate(timeStamp: String): String {
    val timeMillis = Instant.parse(timeStamp).toEpochMilli()
    val dayDifference = getDateDifference(timeMillis)
    return when {
        dayDifference == 0L -> "Today - ${getTime(timeMillis)}"
        dayDifference == 1L -> "Tomorrow - ${getTime(timeMillis)}"
        dayDifference == -1L -> "Yesterday - ${getTime(timeMillis)}"
        dayDifference < -1L -> "$dayDifference Days Ago"
        else -> "In $dayDifference days"
    }
}