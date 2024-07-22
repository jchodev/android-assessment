package com.jerry.assessment.ext

import java.text.SimpleDateFormat
import java.util.Date

fun Long.convertTimeFormat(): String {
    val seconds = this / 1000
    val minutes = seconds / 60
    val hours = minutes / 60

    val remainingSeconds = seconds % 60
    val remainingMinutes = minutes % 60

    return when {
        hours > 0 -> String.format("%02d:%02d:%02d", hours, remainingMinutes, remainingSeconds)
        else -> String.format("%02d:%02d", remainingMinutes, remainingSeconds)
    }
}

//ref: https://android.googlesource.com/platform/frameworks/base/+/0e2d281/core/java/android/text/format/DateUtils.java {function : formatElapsedTime}
fun Long.toDuration(): String {
    var hours: Long = 0
    var minutes: Long = 0
    var seconds: Long = this
    if (this >= 3600) {
        hours = this / 3600
        seconds -= hours * 3600
    }
    if (seconds >= 60) {
        minutes = seconds / 60
        seconds -= minutes * 60
    }

    return when {
        hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
        else -> String.format("%02d:%02d", minutes, seconds)
    }
}

fun Long.toDateString(): String {
    if (this == 0L){
        return "---"
    }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = Date(this * 1000)
    return dateFormat.format(date)
}
