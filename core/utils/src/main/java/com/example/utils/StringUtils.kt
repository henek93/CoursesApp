package com.example.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(): Date {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.parse(this) ?: Date()
}

fun Date.formatToRussian(): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.format(this)
}