package com.example.firstkotlinproject.data.locale.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstkotlinproject.data.locale.room.dao.MovieDao
import com.example.firstkotlinproject.data.locale.room.dao.MovieDetailsDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [MovieDbEntity::class, MovieDetailsDbEntity::class, GenreDbEntity::class, ActorDbEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getMoviesDao(): MovieDao
    abstract fun getMovieDetailsDao(): MovieDetailsDao

    @InternalCoroutinesApi
    companion object{
        private var instance: AppDataBase? = null
        private const val DATABASE_NAME = "Films.db"

//        val instance: AppDataBase by lazy {
//            Room.databaseBuilder(
//                this,
//                AppDataBase::class.java,
//                DATABASE_NAME,
//            ).build()
//        }

        fun getInstance(context: Context): AppDataBase {
            if (instance == null){
                synchronized(AppDataBase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDataBase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return instance!!
        }
    }

}