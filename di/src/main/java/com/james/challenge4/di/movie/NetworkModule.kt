package com.james.challenge4.di.movie


import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.james.challenge4.data.source.remote.movie.network.ApiService
import com.james.challenge4.di.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
                .header("Authorization", "Bearer "+ BuildConfig.TMDB_TOKEN )
                .build()
             chain.proceed(request)
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(serviceInterceptor())
            .addInterceptor(provideChuckerInterceptor(context))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient) : ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_TMDB)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)

    }

    private fun provideChuckerInterceptor(context: Context): Interceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}

