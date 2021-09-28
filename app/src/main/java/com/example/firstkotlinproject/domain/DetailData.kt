package com.example.firstkotlinproject.domain

import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.MovieDetail

class DetailData {
    fun getDetail(): List<MovieDetail>{
        return listOf(
            MovieDetail(R.drawable.orig,"Avengers \nnEnd Game","13+","Action, Adventure, Fantasy",
                R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_star_grey,
            "125 Reviews",R.string.storyline,R.drawable.daun,R.drawable.evans,R.drawable.thor,R.drawable.ruffalo,"Robert Downey Jr.","Chris Evans","Mark Ruffalo","Chris Hemsworth")
        )
    }
}