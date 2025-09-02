package com.example.domain.entity

import java.util.Date

data class Course(
    val id: String,
    val title: String,
    val text: String,
    val price: String,
    val rate: Float,
    val startDate: Date,
    val hasLike: Boolean,
    val publishDate: Date
)

