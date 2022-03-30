package com.fr1014.http

import android.util.Log
import retrofit2.Retrofit
import java.lang.Exception
import java.lang.RuntimeException

/**
 * Create by fanrui07
 * Date: 2022/3/29
 * Describe:
 */
object WanAndroidApi {

    lateinit var retrofit: Retrofit

    val bannerApi by lazy {
        create<IBannerApi>()
    }

    fun init(builder: Retrofit.Builder) {
        if (!::retrofit.isInitialized) {
            builder.baseUrl("https://www.wanandroid.com/")
            retrofit = builder.build()
        }
    }

    inline fun <reified T> create(): T {
        try {
            return retrofit.create(T::class.java)
        } catch (e: Exception) {
            Log.d("hello", "create: $e")
            throw RuntimeException("Create service Exception by class!")
        }
    }
}