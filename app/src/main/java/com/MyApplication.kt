package com

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.fr1014.http.HttpErrorCallAdapterFactory
import com.fr1014.http.WanAndroidApi
import com.fr1014.utils.CheckObserver
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Time: 2022/2/17
 * Author: fanrui07
 * Description:
 */
class MyApplication : Application() {
    private val checkObserver = CheckObserver(this)

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(checkObserver)
        val builder = createRetrofitBuilder()
        WanAndroidApi.init(builder)
    }

    private fun createRetrofitBuilder(): Retrofit.Builder {
        val builder = Retrofit.Builder()
        builder.addConverterFactory(GsonConverterFactory.create())
//        builder.addCallAdapterFactory(HttpErrorCallAdapterFactory.create())
        builder.client(OkHttpClient.Builder().build())
        return builder
    }
}