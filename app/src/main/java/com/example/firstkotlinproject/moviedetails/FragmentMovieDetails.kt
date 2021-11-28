package com.example.firstkotlinproject.moviedetails

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.model.MovieDetails
import com.example.firstkotlinproject.provider.MovieProvider
import kotlinx.coroutines.*


class FragmentMovieDetails : Fragment() {

    private var listener: MovieDetailsBackClickListener? = null
    @DelicateCoroutinesApi
    private val scopeDetails = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val viewDetailModel: MovieDetailViewModel by viewModels{
        MovieDetailViewModelFactory((requireActivity() as MovieProvider).provideMovie())
    }

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

        view.findViewById<RecyclerView>(R.id.recycler_movies).apply {

            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            val adapter = MovieDetailAdapter()

            this.adapter = adapter
        }
        view.findViewById<View>(R.id.back_button_layout).setOnClickListener{
            listener?.onMovieDeselected()
        }
        viewDetailModel.loadMovie(movieData)

            scopeDetails.launch {
                viewDetailModel.loadingMovieDetailLiveData.observe(viewLifecycleOwner, Observer { movie ->
                    if(movie != null){
                        bindUI(view,movie)
                    }else{
                       errorWasFound()
                    }
                })
            }
    }

    private fun errorWasFound(){
        Toast.makeText(requireContext(),"Sorry! Something went wrong - we can not find this movie in the system.", Toast.LENGTH_LONG)
            .show()
    }

    private fun bindUI(view: View, movie:MovieDetails){
        updateMovieDetailsInfo(movie)
        val adapter = view.findViewById<RecyclerView>(R.id.recycler_movies).adapter as MovieDetailAdapter
        adapter.submitList(movie.actors)
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    private fun updateMovieDetailsInfo(movie : MovieDetails) {
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

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        private const val PARAM_MOVIE_DATA = "movie_data"

        fun create(movieData: Int) = FragmentMovieDetails().also {
            val args = bundleOf(
                PARAM_MOVIE_DATA to movieData
            )
            it.arguments = args
        }
    }
}