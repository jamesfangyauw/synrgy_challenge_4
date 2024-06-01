package com.james.challenge4.presentation.photo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.work.WorkInfo
import coil.load
import com.bumptech.glide.Glide

import com.james.challenge4.R
import com.james.challenge4.databinding.FragmentPhotoBinding
import com.nareshchocha.filepickerlibrary.models.PickMediaConfig
import com.nareshchocha.filepickerlibrary.models.PickMediaType
import com.nareshchocha.filepickerlibrary.ui.FilePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding

    private val viewModel: PhotoViewModel by viewModels()

    private lateinit var photo: String

    private val status get() = viewModel.status


    private val filePickerResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::handleFilePickerResult,
        )

    private val requestNotificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
            ::handleRequestNotificationPermissionLauncher
        )


    companion object {
        const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED -> {
                //Continue flow of app
            }

            else -> {
                requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        lifecycleScope.launch {
            viewModel.loadPhoto().first()?.let {
                photo = it
                Glide.with(requireContext())
                    .load(it)
                    .into(binding!!.imageView)
//                binding?.imageView?.load(it)
//                viewModel.setImageUri(it.toUri())
            }

        }


        binding?.goButton?.setOnClickListener {
            viewModel.applyBlur(blurLevel)
            viewModel.setStatusTrue()
        }


        // Hookup the Cancel button
        binding?.cancelButton?.setOnClickListener { viewModel.cancelWork() }

        binding?.pickFileButton?.setOnClickListener { openFilePicker() }

        viewModel.outputWorkInfos.observe(viewLifecycleOwner, workInfosObserver())
    }

    private fun workInfosObserver(): Observer<List<WorkInfo>> {
        return Observer { listOfWorkInfo ->

            // Note that these next few lines grab a single WorkInfo if it exists
            // This code could be in a Transformation in the ViewModel; they are included here
            // so that the entire process of displaying a WorkInfo is in one location.

            // If there are no matching work info, do nothing
            if (listOfWorkInfo.isNullOrEmpty()) {
                return@Observer
            }


            // We only care about the one output status.
            // Every continuation has only one worker tagged TAG_OUTPUT
            val workInfo = listOfWorkInfo[0]

            if (workInfo.state.isFinished) {
                showWorkFinished()

                // Normally this processing, which is not directly related to drawing views on
                // screen would be in the ViewModel. For simplicity we are keeping it here.
                val outputImageUri = workInfo.outputData.getString(KEY_IMAGE_URI)

                // If there is an output file show "See File" button
                if (!outputImageUri.isNullOrEmpty()) {
                    if (status.value ?: false) {
                        viewModel.savePhoto(outputImageUri)
                        viewModel.setOutputUri(outputImageUri)
                        binding?.imageView?.load(outputImageUri)
                        viewModel.setImageUri(outputImageUri.toUri())
                    }
                }
            } else {
                showWorkInProgress()
            }
        }
    }

    /**
     * Shows and hides views for when the Activity is processing an image
     */
    private fun showWorkInProgress() {
        binding?.let {
            with(it) {
                progressBar.visibility = View.VISIBLE
                cancelButton.visibility = View.VISIBLE
                goButton.visibility = View.GONE
            }
        }
    }

    /**
     * Shows and hides views for when the Activity is done processing an image
     */
    private fun showWorkFinished() {
        binding?.let {
            with(it) {
                progressBar.visibility = View.GONE
                cancelButton.visibility = View.GONE
                goButton.visibility = View.VISIBLE
            }
        }
    }

    private
    val blurLevel: Int
        get() =
            when (binding?.radioBlurGroup?.checkedRadioButtonId) {
                R.id.radio_blur_lv_1 -> 1
                R.id.radio_blur_lv_2 -> 2
                R.id.radio_blur_lv_3 -> 3
                else -> 1
            }

    private fun openFilePicker() {
        filePickerResult.launch(
            FilePicker.Builder(requireContext())
                .pickMediaBuild(
                    PickMediaConfig(
                        mPickMediaType = PickMediaType.ImageOnly,
                        allowMultiple = false,
                    ),
                ),
        )
    }

    private fun handleFilePickerResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.setStatusFalse()
            result.data?.data?.let {
                viewModel.savePhoto(it.toString())
//                binding?.imageView?.load(it)
                Glide.with(requireContext())
                    .load(it)
                    .into(binding!!.imageView)
                viewModel.setImageUri(it)
            }
        }
    }

    fun handleRequestNotificationPermissionLauncher(isGranted: Boolean) {
        if (isGranted) {
            Toast.makeText(requireContext(), "permission diterima", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "permission ditolak", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

    }

}

