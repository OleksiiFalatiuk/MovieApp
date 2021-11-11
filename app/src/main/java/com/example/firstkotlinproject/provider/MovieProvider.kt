package com.example.firstkotlinproject.provider

import com.example.firstkotlinproject.data.MovieRepository

internal interface MovieProvider {
    fun provideMovie(): MovieRepository
}