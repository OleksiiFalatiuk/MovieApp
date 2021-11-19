package com.example.firstkotlinproject.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.R
import com.example.firstkotlinproject.data.MovieRepository

import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.provider.MovieProvider
import kotlinx.coroutines.*


class FragmentMovieList : Fragment() {
    @DelicateCoroutinesApi
    private val scopeList = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val adapter: MovieListAdapter? = null

    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((requireActivity() as MovieProvider).provideMovie())
    }

    private var listener: MoviesListItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MoviesListItemClickListener) {
            listener = context
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)

        return view
    }

    @DelicateCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<RecyclerView>(R.id.rvActors).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)

            val adapter = MovieListAdapter { movieData ->
                listener?.onMovieSelected(movieData)
            }

            this.adapter = adapter
            loadDataToListAdapter(adapter)
        }
    }

    @DelicateCoroutinesApi
    private fun loadDataToListAdapter(adapter: MovieListAdapter){
//        val provider = (requireActivity() as MovieProvider).provideMovie()
//        scopeList.launch {
//            val moviesData = provider.loadMovies()
//            adapter.submitList(moviesData)
//        }
        scopeList.launch {
            viewModel.loadingMovieLiveData.observe(viewLifecycleOwner, Observer { movieList ->
                adapter.submitList(movieList)
            })
        }
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    interface MoviesListItemClickListener {
        fun onMovieSelected(movieData: Movie)
    }


    companion object {
        fun create() = FragmentMovieList()
    }
}
