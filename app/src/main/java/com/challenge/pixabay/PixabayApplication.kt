package com.challenge.pixabay

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.challenge.pixabay.di.AppModule
import com.challenge.pixabay.di.IAppModule

class PixabayApplication : Application(), ImageLoaderFactory {

    lateinit var appModule: IAppModule

    override fun onCreate() {
        super.onCreate()
        instance = this
        appModule = AppModule()
    }

    companion object {
        lateinit var instance: PixabayApplication
            private set
    }

    fun hasNetwork(): Boolean {
        return instance.isNetworkConnected()
    }

    private fun isNetworkConnected(): Boolean {
        var isConnected = false // Initial Value
        val connectivityManager =
            instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
            isConnected = true
        }
        return isConnected
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(context = this)
            .crossfade(true)
            .memoryCache {
                MemoryCache.Builder(context = this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.025)
                    .build()
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .respectCacheHeaders(false)
            .build()
    }
}
