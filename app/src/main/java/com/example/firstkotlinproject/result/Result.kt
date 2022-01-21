package com.example.firstkotlinproject.result

import com.example.firstkotlinproject.model.MovieDetails


sealed class Result<out T>

 data class Success<T>(val data: T) : Result<T>()

 data class Error(val message: String) : Result<Nothing>()


inline fun <R> checkResult(block: () -> R): Result<R> {
    return try {
        Success(block())
    }finally {
        print("Check your network connection!")
    }
}

inline fun <R> checkResultDetails(block: () -> R): Result<R> {
    return try {
        Success(block())
    }finally {
        print("Check your network connection!")
    }
}


