package com.james.challenge4.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.james.challenge4.R
import com.james.challenge4.presentation.login.LoginActivity
import com.james.challenge4.presentation.dialog.PopDialog
import com.james.challenge4.databinding.FragmentHomeBinding
import com.james.challenge4.domain.model.Note
import com.james.challenge4.presentation.NoteParcelize
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() =  _binding

    private lateinit var homeAdapter: HomeAdapter
    private val viewModel : HomeViewModel by viewModels()

    companion object{
        const val EXTRA_DATA = "extra"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = requireActivity().intent.getStringExtra(EXTRA_DATA)
        lifecycleScope.launch {
            binding?.name?.text = "Hello, " + viewModel.getName().first()
        }


        binding?.btwExit?.setOnClickListener{
//            throw RuntimeException("Test Crash") // Force a crash
            viewModel.logOut()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
            requireActivity().finish()
        }

        homeAdapter = HomeAdapter(requireContext())

        binding?.rvNote?.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        homeAdapter.setOnItemClick {
            moveToAdd(it)
        }


        homeAdapter.setOnDelClick {
            val pop = PopDialog()
            pop.showDialog(requireContext(),
                positive = {_,_ ->
                    viewModel.deleteNote(Note(it.id, it.title, it.content))
                }, negative = {_,_->

                },
                title = getString(R.string.sure_delete_note),
                subTitle = getString(R.string.sure_delete_note_sub)
            )
        }

        viewModel.getAllNote()?.observe(requireActivity()){
            if (it.isNotEmpty()){
                binding?.rvNote?.visibility = View.VISIBLE
                homeAdapter.submitList(it)
            } else {
                binding?.rvNote?.visibility = View.GONE
                binding?.tvNoData?.visibility = View.VISIBLE
            }
        }
    }

    private fun moveToAdd(note: NoteParcelize?){
        val move = HomeFragmentDirections.actionHomeFragmentToAddEditNote(note)
        findNavController().navigate(move)
    }
}