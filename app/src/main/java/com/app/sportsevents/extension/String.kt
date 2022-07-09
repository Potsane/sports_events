package com.app.sportsevents.extension

import com.app.sportsevents.utils.dateStringToTimeMillis
import com.app.sportsevents.utils.isDateTomorrow

fun String.toTimeMillis() = dateStringToTimeMillis(this)

fun String.isTomorrow() = isDateTomorrow(this.toTimeMillis())