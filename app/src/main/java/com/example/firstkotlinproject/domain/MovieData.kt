package com.example.firstkotlinproject.domain

import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.ActorData
import com.example.firstkotlinproject.data.Movie

object MovieData {
    fun getMovie() = arrayListOf(
        Movie(
            1,
            13,
            "Avengers: End Game",
            "Action, Adventure, Drama",
            "137 min",
            125,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.avengers,
            R.drawable.orig,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos\' actions and restore balance to the universe.",
            arrayListOf(
                ActorData(1, "Robert Downey Jr.", R.drawable.daun),
                ActorData(2, "Chris Evans", R.drawable.evans),
                ActorData(3, "Mark Ruffalo", R.drawable.ruffalo),
                ActorData(4, "Chris Hemsworth", R.drawable.thor),
            )
        ),
        Movie(
            2,
            16,
            "Tenet",
            "Action, Sci-Fi, Thriller",
            "97 min",
            98,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_star_grey,
            R.drawable.tenet,
            R.drawable.orig,
            "A secret agent embarks on a dangerous, time-bending mission to prevent the start of World War III.",
            arrayListOf(
                ActorData(1, "Robert Downey Jr.", R.drawable.daun),
                ActorData(2, "Chris Evans", R.drawable.evans),
                ActorData(3, "Mark Ruffalo", R.drawable.ruffalo),
                ActorData(4, "Chris Hemsworth", R.drawable.thor),
            )
        ),
        Movie(
            3,
            13,
            "Black Widow",
            "Action, Adventure, Sci-Fi",
            "102 min",
            38,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.bw,
            R.drawable.orig,
            "At birth the Black Widow (aka Natasha Romanova) is given to the KGB, which grooms her to become its ultimate operative. When the U.S.S.R. breaks up, the government tries to kill her as the action moves to present-day New York, where she is a freelance operative.",
            arrayListOf(
                ActorData(1, "Robert Downey Jr.", R.drawable.daun),
                ActorData(2, "Chris Evans", R.drawable.evans),
                ActorData(3, "Mark Ruffalo", R.drawable.ruffalo),
                ActorData(4, "Chris Hemsworth", R.drawable.thor),
            )
        ),
        Movie(
            4,
            13,
            "Wonder Woman 1984",
            "Action, Adventure, Fantasy",
            "120 min",
            74,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_baseline_star_rate_24,
            R.drawable.ic_star_grey,
            R.drawable.ww84,
            R.drawable.orig,
            "Wonder Woman squares off against Maxwell Lord and the Cheetah, a villainess who possesses superhuman strength and agility.",
            arrayListOf(
                ActorData(1, "Robert Downey Jr.", R.drawable.daun),
                ActorData(2, "Chris Evans", R.drawable.evans),
                ActorData(3, "Mark Ruffalo", R.drawable.ruffalo),
                ActorData(4, "Chris Hemsworth", R.drawable.thor),
            )
        )
    )
}