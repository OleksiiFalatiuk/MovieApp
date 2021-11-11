package com.example.firstkotlinproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlinproject.adapters.MovieListAdapter
import com.example.firstkotlinproject.model.Movie
import com.example.firstkotlinproject.provider.MovieProvider
import kotlinx.coroutines.*

//import com.example.firstkotlinproject.domain.MovieData


class FragmentMovieList : Fragment() {
    @DelicateCoroutinesApi
    private val scopeList = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var recycler: RecyclerView? = null

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
//        recycler = view.findViewById(R.id.rvActors)
//        recycler?.adapter = MovieListAdapter()
//        (recycler?.adapter as? MovieListAdapter)?.itemclick = this
        view.findViewById<RecyclerView>(R.id.rvActors).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)

            val adapter = MovieListAdapter { movieData ->
                listener?.onMovieSelected(movieData)
            }

            this.adapter = adapter
            loadDataToListAdapter(adapter)
//            adapter.submitList(MovieData.getMovie())
        }

    }

    @DelicateCoroutinesApi
    private fun loadDataToListAdapter(adapter: MovieListAdapter){
        val provider = (requireActivity() as MovieProvider).provideMovie()
        scopeList.launch {
            val moviesData = provider.loadMovies()
            adapter.submitList(moviesData)
        }
    }

//    override fun onStart() {
//        updateData()
//        super.onStart()
//    }

//    private fun updateData() {
//        (recycler?.adapter as? MovieListAdapter)?.apply {
//            bindActors(MovieData().getMovie())
//        }
//    }
//
//    override fun onItemClick(movieData: Movie) {
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(
//                R.id.container,
//                FragmentMoviesDetails.create(movieData),
//                FragmentMoviesDetails::class.java.simpleName
//            )
//            ?.addToBackStack("trans:${FragmentMoviesDetails::class.java.simpleName}")
//            ?.commit()
//        }


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
