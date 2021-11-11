package com.example.firstkotlinproject

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstkotlinproject.adapters.MovieDetailAdapter
import com.example.firstkotlinproject.model.Movie


class FragmentMoviesDetails : Fragment() {

    var listener: MovieDetailsBackClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieDetailsBackClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieData = arguments?.getSerializable(PARAM_MOVIE_DATA) as? Movie ?: return

        updateMovieDetailsInfo(movieData)

        view.findViewById<RecyclerView>(R.id.recycler_movies).apply {

            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            val adapter = MovieDetailAdapter()

            this.adapter = adapter

            adapter.submitList(movieData.actors)
        }

    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    private fun updateMovieDetailsInfo(movie : Movie) {
//        view?.findViewById<ImageView>(R.id.imageDetail)
//            ?.setImageResource(movie.detailImageRes)
        Glide.with(this).load(movie.detailImageRes).into(view?.findViewById(R.id.imageDetail))

        view?.findViewById<TextView>(R.id.yearsDetail)?.text =
            context?.getString(R.string._13, movie.years)

        view?.findViewById<TextView>(R.id.movie_name_text)?.text = movie.name
        view?.findViewById<TextView>(R.id.tag)?.text = movie.genre.joinToString { it.name }
        view?.findViewById<TextView>(R.id.detailReviews)?.text =
            context?.getString(R.string._125_reviews, movie.review)
        view?.findViewById<TextView>(R.id.detailStory)?.text = movie.storyLine

        val starsImages = listOf<ImageView?>(
            view?.findViewById(R.id.detailStar1),
            view?.findViewById(R.id.detailStar2),
            view?.findViewById(R.id.detailStar3),
            view?.findViewById(R.id.detailStar4),
            view?.findViewById(R.id.detailStar5)
        )
        starsImages.forEachIndexed { index, imageView ->
            imageView?.let {
                val colorId =
                    if (movie.rating > index) R.color.pink else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }


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

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        private const val PARAM_MOVIE_DATA = "movie_data"

        fun create(movieData : Movie) = FragmentMoviesDetails().also {
            val args = bundleOf(
                PARAM_MOVIE_DATA to movieData
            )
            it.arguments = args
        }
    }
}