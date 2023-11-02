package com.dayker.viewed.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM, yyyy", Locale.ENGLISH)
    return format.format(date)
}

fun Long.secondsToMinutes(): Long {
    val minutesDivisor = 60
    return (this / minutesDivisor)
}