package com.fr1014.coroutine

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.fr1014.keeplearning.BaseVBActivity
import com.fr1014.keeplearning.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : BaseVBActivity<ActivityCoroutineBinding>() {

    override fun getViewBinding() = ActivityCoroutineBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
         *  运行结果
         *  onCreate: start
         *  onCreate: end
         *  Coroutine 1: DefaultDispatcher-worker-1
         *  Coroutine 2: main
         *  Coroutine 3: DefaultDispatcher-worker-1
         *  Coroutine 4: main
         *  协程为非阻塞的
         */
        Log.d("hello", "onCreate: start")

        lifecycleScope.launch {

            //IO线程
            withContext(Dispatchers.IO) {
                //IO线程
                Log.d("hello", "Coroutine 1: " + Thread.currentThread().name)

                //UI线程
                withContext(Dispatchers.Main) {
                    Log.d("hello", "Coroutine 2: " + Thread.currentThread().name)
                }

                //IO线程
                Log.d("hello", "Coroutine 3: " + Thread.currentThread().name)
            }

            //UI线程
            Log.d("hello", "Coroutine 4: " + Thread.currentThread().name)
        }

        Log.d("hello", "onCreate: end")
    }
}