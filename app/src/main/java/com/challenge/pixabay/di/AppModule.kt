package com.challenge.pixabay.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.challenge.pixabay.PixabayApplication
import com.challenge.pixabay.common.Constants.BASE_URL
import com.challenge.pixabay.common.Constants.cacheSize
import com.challenge.pixabay.data.common.Constants
import com.challenge.pixabay.data.repository.PhotosRepository
import com.challenge.pixabay.domain.repository.IPhotosRepository
import com.challenge.pixabay.data.remote.IPixabayApi
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

class AppModule : IAppModule {

    override val providePhotosRepository: IPhotosRepository by lazy {
        PhotosRepository(providePixabayApi)
    }

    private val providePixabayApi: IPixabayApi by lazy {
        retrofit().create(IPixabayApi::class.java)
    }

    private fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun okHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache())
            .addInterceptor(httpLoggingInterceptor())
            .addNetworkInterceptor(NetworkInterceptor())
            .addInterceptor(OfflineInterceptor())
            .build()
    }

    private fun cache(): Cache {
        return Cache(File(PixabayApplication.instance.cacheDir, "cache"), cacheSize)
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    companion object {

        class NetworkInterceptor : Interceptor {
            private val TAG = "NetworkInterceptor"

            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "called.")

                val response: Response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                    .maxAge(1, TimeUnit.MINUTES)
                    .build()

                return response.newBuilder()
                    .removeHeader(Constants.HEADER_PRAGMA)
                    .removeHeader(Constants.HEADER_CACHE_CONTROL)
                    .header(Constants.HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }

        class OfflineInterceptor : Interceptor {
            private val TAG = "OfflineInterceptor"


            override fun intercept(chain: Interceptor.Chain): Response {
                Log.d(TAG, "called.")


                var request = chain.request()

                if(!PixabayApplication.instance.hasNetwork())
                {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                    request = request.newBuilder()
                        .removeHeader(Constants.HEADER_PRAGMA)
                        .removeHeader(Constants.HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }

                return chain.proceed(request)
            }
        }
    }
}