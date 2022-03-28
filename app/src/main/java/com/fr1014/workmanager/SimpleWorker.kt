package com.fr1014.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Create by fanrui07
 * Date: 2022/3/28
 * Describe:
 */
class SimpleWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {

    /*
     * doWork()方法不在主线程，可执行耗时任务
     */
    override fun doWork(): Result {
        Log.d("hello", "SimpleWorker: do work in SimpleWorker")
        return Result.success()
    }

}