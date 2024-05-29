package com.james.challenge4.presentation.movie

import android.os.Bundle
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

                            }
                        }
                    }
                    is ApiResponse.Error -> {
                    }
                    is ApiResponse.Empty -> {}
                }
            }
        }

    }
}