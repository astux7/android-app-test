package com.basta.demo.extentions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toDateFormat(): String {
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    val dateTime = LocalDateTime.parse(this, formatter)

    val yearMonthDayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return yearMonthDayFormatter.format(dateTime)
}
