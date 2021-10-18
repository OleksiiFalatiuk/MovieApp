package com.example.firstkotlinproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.adapters.MovieDetailAdapter
import com.example.firstkotlinproject.data.Movie


class FragmentMoviesDetails : Fragment() {

    private var recyclerDetail: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieData = arguments?.getSerializable(PARAM_MOVIE_DATA) as? Movie ?: return

//        updateMovieDetailsInfo(movieData)

        view.findViewById<RecyclerView>(R.id.recycler_movies).apply {

            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            val adapter = MovieDetailAdapter()

            this.adapter = adapter

            adapter.submitList(movieData.actors)
        }

    }

    private fun updateMovieDetailsInfo(movie : Movie) {
        view?.findViewById<ImageView>(R.id.imageDetail)
            ?.setImageResource(movie.detailImageRes)

        view?.findViewById<TextView>(R.id.yearsDetail)?.text =
            context?.getString(R.string.movies_list_allowed_age_template, movie.pgAge)

        view?.findViewById<TextView>(R.id.movie_name_text)?.text = movie.title
        view?.findViewById<TextView>(R.id.movie_tags_text)?.text = movie.genre
        view?.findViewById<TextView>(R.id.movie_reviews_count_text)?.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        view?.findViewById<TextView>(R.id.movie_storyline_text)?.text = movie.storyLine

        val starsImages = listOf<ImageView?>(
            view?.findViewById(R.id.star1_image),
            view?.findViewById(R.id.star2_image),
            view?.findViewById(R.id.star3_image),
            view?.findViewById(R.id.star4_image),
            view?.findViewById(R.id.star5_image)
        )
    }

//    override fun onStart() {
//        updateData()
//        super.onStart()
//    }
//
//    private fun updateData() {
//        (recyclerDetail?.adapter as? MovieDetailAdapter)?.apply {
//            bindActorsDetail(DetailData().getDetail())
//        }
//    }

    companion object {
        private const val PARAM_MOVIE_DATA = "movie_data"

        fun create(movie : Movie) = FragmentMoviesDetails().also {
            val args = bundleOf(
                PARAM_MOVIE_DATA to movie
            )
            it.arguments = args
        }
    }


}