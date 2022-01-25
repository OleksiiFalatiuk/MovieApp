package com.example.firstkotlinproject.data.locale.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstkotlinproject.data.locale.room.dao.MovieDao
import com.example.firstkotlinproject.data.locale.room.dao.MovieDetailsDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [MovieDbEntity::class, MovieDetailsDbEntity::class, GenreDbEntity::class, ActorDbEntity::class], version = 6)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getMoviesDao(): MovieDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    @InternalCoroutinesApi
    companion object{
        private var instance: AppDataBase? = null
        private const val DATABASE_NAME = "Films.db"


        fun getInstance(context: Context): AppDataBase {
            if (instance == null){
                synchronized(AppDataBase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDataBase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance!!
        }
    }

}