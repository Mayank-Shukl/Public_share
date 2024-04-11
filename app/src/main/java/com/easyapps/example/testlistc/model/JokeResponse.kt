package com.easyapps.example.testlistc.model

/**
 * joke response data class
 */
data class JokeResponse(
    val id: Int,
    val setup: String,
    val delivery: String,
    val category: String,
)
