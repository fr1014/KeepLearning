package com.fr1014.coroutine

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityCoroutine2Binding
import kotlinx.coroutines.*

class Coroutine2Activity : BaseActivity() {
    private val binding by inflate<ActivityCoroutine2Binding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*----------------------------运用统一的job去管理协程------------------------------------*/
        val job = Job()
        val scope = CoroutineScope(job)
        scope.launch {

        }
        job.cancel()
        /*----------------------------运用统一的job去管理协程------------------------------------*/

        /*
         * lifecycleScope.launch {
         * //协程作用域
         * }
         */
        lifecycleScope.launch {

            //子协程A
            launch {
                Log.d("hello", "协程A")
                delay(1000)
                Log.d("hello", "协程A Finish")
            }

            //子协程B
            launch {
                Log.d("hello", "协程B")
                delay(1500)
                Log.d("hello", "协程B Finish")
            }

            logDot()
        }

        Log.d("hello", "onCreate Finish")
    }

    suspend fun sum() = 5 + 5

    suspend fun logDot() {
        coroutineScope {
            launch {
                Log.d("hello", "logDot: ")
            }

        }
    }
}