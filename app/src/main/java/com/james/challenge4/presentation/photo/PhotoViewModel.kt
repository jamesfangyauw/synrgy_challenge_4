package com.james.challenge4.presentation.photo

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.james.challenge4.R
import com.james.challenge4.domain.usecase.PhotoUseCase
import com.james.challenge4.presentation.photo.PhotoFragment.Companion.KEY_IMAGE_URI
import com.james.challenge4.worker.BlurWorker
import com.james.challenge4.worker.CleanupWorker
import com.james.challenge4.worker.SaveImageToFileWorker
import com.james.challenge4.worker.TAG_OUTPUT
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    @ApplicationContext application: Context,
    private val photoUseCase: PhotoUseCase
) : ViewModel() {

    companion object {
        private const val IMAGE_MANIPULATION_WORK_NAME = "image_manipulation_work"
    }

    private var imageUri: Uri? = null
    internal var outputUri: Uri? = null
    private val workManager = WorkManager.getInstance(application)
    internal val outputWorkInfos: LiveData<List<WorkInfo>> =
        workManager.getWorkInfosByTagLiveData(TAG_OUTPUT)

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status



    init {
        // This transformation makes sure that whenever the current work Id changes the WorkInfo
        // the UI is listening to changes
        imageUri = getImageUri(application.applicationContext)

    }

    internal fun cancelWork() {
        workManager.cancelUniqueWork(IMAGE_MANIPULATION_WORK_NAME)
    }

    /**
     * Creates the input data bundle which includes the Uri to operate on
     * @return Data which contains the Image Uri as a String
     */
    private fun createInputDataForUri(): Data {
        val builder = Data.Builder()
        imageUri?.let {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
        }
        return builder.build()
    }

    /**
     * Create the WorkRequest to apply the blur and save the resulting image
     * @param blurLevel The amount to blur the image
     */
    internal fun applyBlur(blurLevel: Int) {
        // Add WorkRequest to Cleanup temporary images
        var continuation = workManager
            .beginUniqueWork(
                IMAGE_MANIPULATION_WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequest.from(CleanupWorker::class.java)
            )

        // Add WorkRequests to blur the image the number of times requested
        for (i in 0 until blurLevel) {
            val blurBuilder = OneTimeWorkRequestBuilder<BlurWorker>()

            // Input the Uri if this is the first blur operation
            // After the first blur operation the input will be the output of previous
            // blur operations.
            if (i == 0) {
                blurBuilder.setInputData(createInputDataForUri())
            }

            continuation = continuation.then(blurBuilder.build())
        }

        // Create charging constraint
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .build()

        // Add WorkRequest to save the image to the filesystem
        val save = OneTimeWorkRequestBuilder<SaveImageToFileWorker>()
            .setConstraints(constraints)
            .addTag(TAG_OUTPUT)
            .build()
        continuation = continuation.then(save)

        // Actually start the work
        continuation.enqueue()
    }

    private fun uriOrNull(uriString: String?): Uri? {
        return if (!uriString.isNullOrEmpty()) {
            Uri.parse(uriString)
        } else {
            null
        }
    }

    private fun getImageUri(context: Context): Uri {
        val resources = context.resources

        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.ic_launcher_background))
            .appendPath(resources.getResourceTypeName(R.drawable.ic_launcher_background))
            .appendPath(resources.getResourceEntryName(R.drawable.ic_launcher_background))
            .build()
    }

    internal fun setOutputUri(outputImageUri: String?) {
        outputUri = uriOrNull(outputImageUri)
    }

    fun setImageUri(imageUri: Uri) {
        this.imageUri = imageUri
    }

    fun savePhoto(photo: String) {
        viewModelScope.launch {
            photoUseCase.savePhoto(photo)
        }
    }

    suspend fun loadPhoto(): Flow<String?> {
        return flow {
            val photo = photoUseCase.loadPhoto()
            emit(photo)
        }
    }

    fun setStatusTrue() {
        _status.value = true
    }

    fun setStatusFalse() {
        _status.value = false
    }
}