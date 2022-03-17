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
         *  协程为非阻塞的
         */
//        lifecycle.coroutineScope.launch {
//
//        }
        Log.d("hello", "onCreate: start")
        lifecycleScope.launch {
            coroutineFunc()
        }
        Log.d("hello", "onCreate: end")
    }

    /*
     * withContext(Dispatchers.IO) 创建了一个指定在 IO 线程池中运行的代码块，该区间内的任何代码都始终通过 IO 线程来执行
     *
     */
    private suspend fun coroutineFunc() {
        //IO线程
        withContext(Dispatchers.IO) {
            //IO线程
            Log.d("hello", "Coroutine 1: " + Thread.currentThread().name)
        }

        //UI线程
        Log.d("hello", "Coroutine 3: " + Thread.currentThread().name)
    }

}