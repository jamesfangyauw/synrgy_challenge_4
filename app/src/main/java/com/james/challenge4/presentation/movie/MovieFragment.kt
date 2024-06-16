package com.james.challenge4.presentation.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MovieFragment", "onCreate" )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding?.root
        Log.d("MovieFragment", "onCreateView" )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MovieFragment", "onViewCreated1" )
        viewModel.movie.observe(viewLifecycleOwner){apiResponse ->
            if (apiResponse!= null){
                when(apiResponse){
                    is ApiResponse.Success -> {
                        movieAdapter.setData(apiResponse.data)
                        binding?.let {
                            with(it.rvMovie) {
                                layoutManager = LinearLayoutManager(context)
                                setHasFixedSize(true)
                                adapter = movieAdapter
                                Log.d("MovieFragment", "observe" )
                            }
                        }
                    }
                    is ApiResponse.Error -> {
                    }
                    is ApiResponse.Empty -> {}
                }
            }
        }

        Log.d("MovieFragment", "onViewCreated2" )

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("MovieFragment", "onViewStateRestored" )
    }

    override fun onStart() {
        super.onStart()
        Log.d("MovieFragment", "onStart" )
    }

    override fun onResume() {
        super.onResume()
        Log.d("MovieFragment", "onResume" )
    }

    override fun onPause() {
        super.onPause()
        Log.d("MovieFragment", "onPause" )
    }

    override fun onStop() {
        super.onStop()
        Log.d("MovieFragment", "onStop" )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("MovieFragment", "onSaveInstanceState" )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MovieFragment", "onDestoryView" )
    }


}