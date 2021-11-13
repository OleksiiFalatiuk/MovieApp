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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstkotlinproject.adapters.MovieDetailAdapter
import com.example.firstkotlinproject.model.Actor
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.provider.MovieProvider
import kotlinx.coroutines.*


class FragmentMoviesDetails : Fragment() {

    var listener: MovieDetailsBackClickListener? = null
    @DelicateCoroutinesApi
    private val scopeDetails = CoroutineScope(Dispatchers.Main + SupervisorJob())

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

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movieData = arguments?.getSerializable(PARAM_MOVIE_DATA) as? Int ?: return

//        updateMovieDetailsInfo(movieData)

        view.findViewById<RecyclerView>(R.id.recycler_movies).apply {

            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            val adapter = MovieDetailAdapter()

            this.adapter = adapter

//            adapter.submitList(movieData.actors)
            scopeDetails.launch {
                val provider = (requireActivity() as MovieProvider).provideMovie()
                val detailsData = provider.loadMovie(movieData)
                if (detailsData != null){
                    bindUI(view,detailsData)
                }else{
                    errorWasFound()
                }
            }
        }

    }

    private fun errorWasFound(){
        Toast.makeText(requireContext(),"Sorry! Something went wrong - we can not find this movie in the system.", Toast.LENGTH_LONG)
            .show()
    }

    private fun bindUI(view: View, movie:Movie){
        updateMovieDetailsInfo(movie)
        val adapter = view.findViewById<RecyclerView>(R.id.recycler_movies).adapter as MovieDetailAdapter
        adapter.submitList(movie.actors)
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    private fun updateMovieDetailsInfo(movie : Movie) {
//        view?.findViewById<ImageView>(R.id.imageDetail)
//            ?.setImageResource(movie.detailImageRes)
        val context = view?.context
        if (context != null) {
            view?.findViewById<ImageView>(R.id.imageDetail)?.let {
                Glide.with(context).load(movie.detailImageRes).into(
                    it
                )
            }
        }

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

        fun create(movieData: Int) = FragmentMoviesDetails().also {
            val args = bundleOf(
                PARAM_MOVIE_DATA to movieData
            )
            it.arguments = args
        }
    }
}