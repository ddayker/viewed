package com.dayker.viewed.watchedmovies.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH)
    return format.format(date)
}

fun String.toLongDate(): Long {
    val format = SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH)
    val date = format.parse(this)
    return date?.time ?: 0L
}

fun Long.secondsToMinutes(): Long {
    val minutesDivisor = 60
    return (this / minutesDivisor)
}

fun Long.minutesToSeconds(): Long {
    val secondsMultiplier = 60
    return this * secondsMultiplier
}
