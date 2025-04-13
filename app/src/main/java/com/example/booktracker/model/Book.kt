package com.example.booktracker.model

data class Book(
    val title: String,
    val author: String,
    val description: String = "No brief introduction",
    var isFavorite: Boolean = false
)
