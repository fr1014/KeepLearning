package com

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.fr1014.utils.CheckObserver

/**
 * Time: 2022/2/17
 * Author: fanrui07
 * Description:
 */
class MyApplication : Application() {
    private val checkObserver = CheckObserver()


    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(checkObserver)
    }
}