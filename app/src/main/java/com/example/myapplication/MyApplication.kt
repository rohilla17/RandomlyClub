package com.example.myapplication

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType

public class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        val memoryCacheSize: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            val memClass = (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .memoryClass
            memoryCacheSize = memClass / 8 * 1024 * 1024
        } else {
            memoryCacheSize = 2 * 1024 * 1024
        }

        val config = ImageLoaderConfiguration.Builder(
            this
        ).threadPoolSize(5)
            .threadPriority(Thread.NORM_PRIORITY - 2)
            .memoryCacheSize(memoryCacheSize)
            .memoryCache(FIFOLimitedMemoryCache(memoryCacheSize - 1000000))
            .denyCacheImageMultipleSizesInMemory()
            .discCacheFileNameGenerator(Md5FileNameGenerator())
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .build()

        ImageLoader.getInstance().init(config)
    }
}