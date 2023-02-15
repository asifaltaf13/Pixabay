package com.challenge.pixabay

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.challenge.pixabay.di.IAppModule
import com.challenge.pixabay.di.TestAppModule
import com.google.android.material.color.DynamicColors

class PixabayApplication : Application(), ImageLoaderFactory {

    lateinit var appModule: IAppModule

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        instance = this
        appModule = TestAppModule()
    }

    companion object {
        lateinit var instance: PixabayApplication
            private set
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
