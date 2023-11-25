package com.dayker.viewed.watched.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return format.format(date)
}

fun String.toLongDate(): Long {
    return try {
        val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val date = format.parse(this)
        date?.time ?: 0L
    } catch (e: Exception) {
        0L
    }
}

fun Long.secondsToMinutes(): Long {
    val minutesDivisor = 60
    return (this / minutesDivisor)
}

fun Long.minutesToSeconds(): Long {
    val secondsMultiplier = 60
    return this * secondsMultiplier
}
