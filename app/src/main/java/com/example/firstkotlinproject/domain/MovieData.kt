package com.example.firstkotlinproject.domain

import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.Movie

class MovieData {
    fun getMovie(): List<Movie>{
        return listOf(
            Movie(R.drawable.avengers,"+13",R.drawable.ic_baseline_favorite_24,"Action, Adventure, Fantasy",R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,
                R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_star_grey,"125 Reviews","Avengers: End Game","137 min"),
            Movie(R.drawable.tenet,"+16",R.drawable.heart_pink,"Action, Sci-Fi, Thriller",R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,
                R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,"98 Reviews","Tenet","97 min"),
            Movie(R.drawable.bw,"+13",R.drawable.ic_baseline_favorite_24,"Action, Adventure, Sci-Fi",R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,
                R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_star_grey,"38 Reviews","Black Widow","102 min"),
            Movie(R.drawable.ww84,"+13",R.drawable.ic_baseline_favorite_24,"Action, Adventure, Fantasy",R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,
                R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,"74 Reviews","Wonder Woman 1984","120 min"),
            Movie(R.drawable.ww84,"+13",R.drawable.ic_baseline_favorite_24,"Action, Adventure, Fantasy",R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,
                R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,"74 Reviews","Wonder Woman 1984","120 min")

        )
    }
}