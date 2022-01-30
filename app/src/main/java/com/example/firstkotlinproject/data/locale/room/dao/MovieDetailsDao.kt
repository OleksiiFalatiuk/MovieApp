package com.example.firstkotlinproject.data.locale.room.dao

import androidx.room.*
import com.example.firstkotlinproject.data.locale.room.ActorDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieDetailsDbEntity
import com.example.firstkotlinproject.data.locale.room.MovieDetailsWithGenresAndActors

@Dao
interface MovieDetailsDao {
    @Transaction
    @Query("SELECT * FROM MovieDetails")
    fun getMovieDetails(): List<MovieDetailsWithGenresAndActors>

    @Insert
    fun insertMovieDetails(details: List<MovieDetailsDbEntity>)

    @Insert
    fun insertMovieDetailsSecondOne(details: MovieDetailsDbEntity)

    @Insert
    fun insertActorsEntity(actor: List<ActorDbEntity>)

    @Update
    fun updateActorsEntity(actor: List<ActorDbEntity>)

    @Query("delete from Actor where detailsId like :id")
    fun deleteOldActorsFromFilms(id: Int)
}