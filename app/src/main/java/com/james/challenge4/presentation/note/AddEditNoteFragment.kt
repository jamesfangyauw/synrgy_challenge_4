package com.james.challenge4.presentation.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.james.challenge4.domain.model.Note
import com.james.challenge4.databinding.FragmentAddEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditNoteFragment : Fragment() {

    private var _binding: FragmentAddEditNoteBinding? = null
    private val binding
        get() = _binding

    private val viewModel: AddEditNoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditNoteBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = AddEditNoteFragmentArgs.fromBundle(arguments as Bundle).note

        if(note != null){
            binding?.edtTitle?.setText(note.title.toString())
            binding?.edtDescription?.setText(note.content.toString())
            binding?.btnSubmit?.text = "Edit"
        }

        binding?.btnSubmit?.setOnClickListener {
            if (note!=null){
                viewModel.updateNote(
                    Note(
                        title = binding?.edtTitle?.text.toString(),
                        content = binding?.edtDescription?.text.toString(),
                        id = note.id
                    )
                )
            } else {
                if(binding?.edtTitle?.text.isNullOrEmpty()){
                    binding?.edtTitle?.error = "Judul tidak boleh kosoong"
                }else{
                    viewModel.addNote(
                        Note(
                            title = binding?.edtTitle?.text.toString(),
                            content = binding?.edtDescription?.text.toString(),
                            id = null
                        )
                    )
                }

            }
        }

        viewModel.isSuccess.observe(viewLifecycleOwner){isSucces ->
            if(isSucces == true){
                val move =
                   AddEditNoteFragmentDirections.actionAddEditNoteToHomeFragment()
                findNavController().navigate(move) //set popupto and popuptoinclusive in navigation main : https://developer.android.com/guide/navigation/backstack/circular
                                                    // and https://stackoverflow.com/a/62557150

//                findNavController().navigateUp() //alternative to back (https://ocandroid.org/posts/android-navigation-graph-introduction.html)
            }else{
                Toast.makeText(requireContext(), "Error ", Toast.LENGTH_SHORT).show()
            }
        }
    }


}