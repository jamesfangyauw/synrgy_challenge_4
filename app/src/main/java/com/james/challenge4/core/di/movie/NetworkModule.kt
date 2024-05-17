package com.james.challenge4.core.di.movie


import com.james.challenge4.core.data.source.remote.movie.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private fun serviceInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request().newBuilder()
                .header("accept", "application/json")
                .header("Authorization", "Bearer "+ "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYTljMzljNTIxMTcwZTVlNWZjODExOWQ2YTA5MWMyNSIsInN1YiI6IjY1YWZiYjM4ZjhhZWU4MDBjYmIxMjI0MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.T3SgL6QLpS9GXPjrNvaawQLNZhszIv7OHXj0QPkIr5M")
                .build()
             chain.proceed(request)
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(serviceInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient) : ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)

    }
}

