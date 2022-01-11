package com.example.firstkotlinproject.repository

import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.data.locale.LocaleDataSource
import com.example.firstkotlinproject.data.remote.RemoteDataSource
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.model.MovieDetails
import com.example.firstkotlinproject.result.Result
import com.example.firstkotlinproject.result.checkResult
import com.example.firstkotlinproject.result.checkResultDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImplNew(
    private val localData: LocaleDataSource,
    private val remoteData: RemoteDataSource
): MovieRepository {

    override suspend fun loadMovies(): Result<List<Movie>> {
        return checkResult {
            withContext(Dispatchers.IO) {
                val movieDB = localData.loadMovies()
                (if (movieDB.isEmpty()) {
                    val movieFromNetwork = remoteData.loadMovies()
                    localData.insertMovies(movieFromNetwork)
                    movieFromNetwork
                } else {
                    movieDB
                })
            }
        }
    }

    override suspend fun loadMovie(movieId: Int): Result<MovieDetails> {
         val movieDetailsDB = withContext(Dispatchers.IO){
            localData.loadMovie(movieId)
        }
        return if (movieDetailsDB.isNotEmpty()){
            localData.insertMovieDetails(movieDetailsDB)
        }else{
            remoteData.loadMovie(movieId)
        }
    }
}

//return checkResultDetails {
//    withContext(Dispatchers.IO) {
//        val movieDetailsDB = localData.loadMovie(movieId)
//        (if (movieDetailsDB.isEmpty()) {
//            val detailsFromNetwork = remoteData.loadMovie(movieId)
//            localData.insertMovieDetails(listOf(detailsFromNetwork))
//            detailsFromNetwork
//        } else {
//            movieDetailsDB
//        })
//    } as MovieDetails
//}


