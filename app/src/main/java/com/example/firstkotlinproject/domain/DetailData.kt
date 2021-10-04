package com.example.firstkotlinproject.domain

import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.MovieDetail

class DetailData {
    fun getDetail(): List<MovieDetail>{
        return listOf(
            MovieDetail(R.drawable.orig,"Avengers \nEnd Game","13+","Action, Adventure, Fantasy",
                R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_baseline_star_rate_24,R.drawable.ic_star_grey,
            "125 Reviews", "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe.",
                R.drawable.daun,R.drawable.evans,R.drawable.thor,R.drawable.ruffalo,"Robert Downey Jr.","Chris Evans","Mark Ruffalo","Chris Hemsworth"),

        )
    }
}