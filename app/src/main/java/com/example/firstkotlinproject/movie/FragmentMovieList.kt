package com.example.firstkotlinproject.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkManager
import com.example.firstkotlinproject.R

import com.example.firstkotlinproject.provider.MovieProvider
import com.example.firstkotlinproject.workmanager.WorkRequest
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class FragmentMovieList : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private val workRequest = WorkRequest()

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

            val adapter = MovieListAdapter { movieId ->
                listener?.onMovieSelected(movieId)
            }

            this.adapter = adapter
            loadDataToListAdapter(adapter)

            WorkManager.getInstance(requireContext()).enqueue(workRequest.requestUpdater)
        }
    }

    @DelicateCoroutinesApi
    private fun loadDataToListAdapter(adapter: MovieListAdapter){
            lifecycleScope.launchWhenResumed {
                viewModel.loadingMovieLiveData.observe(viewLifecycleOwner, Observer { movieList ->
                    adapter.submitList(movieList)
                })
            }

        lifecycleScope.launchWhenResumed {
            viewModel.errorMessageForMovieLiveData.observe(viewLifecycleOwner, Observer { error ->
                showToast(error)
            })
        }
    }

    private fun showToast(message: String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    interface MoviesListItemClickListener {
        fun onMovieSelected(movieId: Int)
    }


    companion object {
        fun create() = FragmentMovieList()
    }
}
