package com.example.firstkotlinproject.model

import java.io.Serializable

data class Actor(
    val id: Long,
    val name: String,
    val imageRes: Unit,
): Serializable