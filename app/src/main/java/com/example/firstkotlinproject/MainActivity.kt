package com.example.firstkotlinproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firstkotlinproject.model.Movie


class MainActivity : AppCompatActivity(),
    FragmentMovieList.MoviesListItemClickListener,
    FragmentMoviesDetails.MovieDetailsBackClickListener {
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

    override fun onMovieSelected(movieData: Movie) {
        toMovieDetails(movieData)
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

    private fun toMovieDetails(movieData: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.flMain,
                FragmentMoviesDetails.create(movieData),
                FragmentMoviesDetails::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMoviesDetails::class.java.simpleName}")
            .commit()
    }

    private fun backStack() {
        supportFragmentManager.popBackStack()
    }

}
