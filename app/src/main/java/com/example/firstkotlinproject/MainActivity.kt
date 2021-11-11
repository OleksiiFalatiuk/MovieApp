package com.example.firstkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstkotlinproject.data.JsonMovieRepository
import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.provider.MovieProvider


class MainActivity : AppCompatActivity(),
    FragmentMovieList.MoviesListItemClickListener,
    FragmentMoviesDetails.MovieDetailsBackClickListener,MovieProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            toMoviesList()
        }

//        supportFragmentManager.beginTransaction().apply {
//            add(R.id.flMain, FragmentMovieList())
//            commit()
//        }

    }

    override fun onMovieSelected(movie: Movie) {
        toMovieDetails(movie)
    }

    override fun onMovieDeselected() {
        backStack()
    }

    private fun toMoviesList() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.flMain,
                FragmentMovieList.create(),
                FragmentMovieList::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMovieList::class.java.simpleName}")
            .commit()
    }

    private fun toMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flMain,
                FragmentMoviesDetails.create(movie.id),
                FragmentMoviesDetails::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMoviesDetails::class.java.simpleName}")
            .commit()
    }

    private fun backStack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovie(): MovieRepository = jsonMovieRepository

}
