package com.fr1014.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.fr1014.MainActivity

/**
 * Time: 2022/2/17
 * Author: fanrui07
 * Description:
 */
const val LIMIT_TIME = 60 * 60 * 1000L
class CheckObserver(val context: Context) : LifecycleObserver {
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
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }
}