package com.fr1014.utils

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Time: 2022/2/17
 * Author: fanrui07
 * Description:
 */
const val LIMIT_TIME = 60 * 1000L
class CheckObserver : LifecycleObserver {
    private var time = 0L

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        time = System.currentTimeMillis()
        Log.d("hello", "进入后台的时间: $time")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        if (time > 0L && System.currentTimeMillis() - time > LIMIT_TIME) {
            Log.d("hello", "应用进入后台时间 > LIMIT_TIME")
        }
    }
}