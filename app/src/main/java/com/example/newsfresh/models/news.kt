package com.example.newsfresh.models

import androidx.core.net.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class news(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

