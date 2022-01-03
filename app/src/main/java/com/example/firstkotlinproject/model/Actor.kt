package com.example.firstkotlinproject.model

import java.io.Serializable

data class Actor(
    val id: Int,
    val name: String,
    val imageRes: String?
): Serializable