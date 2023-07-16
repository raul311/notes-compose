package com.raul311.notescompose.core.data.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

fun getNoteDateFromTimeStamp(timeStamp: Long): String {
    val currentTime = Timestamp(System.currentTimeMillis())
    val dateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ENGLISH)
    val dateValues = dateFormat.format(timeStamp).split(" ")
    val currentDateValues = dateFormat.format(currentTime).split(" ")
    val time = Timestamp(timeStamp)
    println("raul - note time = $time")

    return if (isTimeStampToday(dateValues, currentDateValues)) {
        return dateValues[0]
    } else {
        return dateValues[1]
    }
}

fun isTimeStampToday(time: List<String>, currentTimestamp: List<String>): Boolean {
    if (time.size == 2 && currentTimestamp.size == 2) {
        return time[1] == currentTimestamp[1]
    }
    return false
}