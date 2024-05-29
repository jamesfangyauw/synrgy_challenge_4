package com.james.challenge4.data.source.remote.movie

import android.util.Log
import com.james.challenge4.domain.model.ApiResponse
import com.james.challenge4.data.source.remote.movie.network.ApiService
import com.james.challenge4.data.utils.DataMapper
import com.james.challenge4.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceMovie @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<Movie>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getAllMovie()
                val dataArray = response.results
                if (dataArray?.isNotEmpty() == true){
                    emit(ApiResponse.Success(DataMapper.mapResponsesToEntities(dataArray)))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}