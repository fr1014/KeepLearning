package com.fr1014.workmanager

import android.os.Bundle
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.fr1014.extensions.inflate
import com.fr1014.extensions.toastShort
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityWorkManagerBinding
import java.util.concurrent.TimeUnit

class WorkManagerActivity : BaseActivity() {
    private val binding by inflate<ActivityWorkManagerBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvDoWork.setOnClickListener {
            "点击执行后台任务".toastShort(this)
            doOneTimeWork()
//            doPeriodicWork()
        }
    }

    /*
     *  Result.retry() -> setBackoffCriteria
     *  setBackoffCriteria三个参数
     *
     *  1、第一个参数则用于指定如果任务再次执行失败，下次重试的时间应该以什么样的形式延迟
     * （假如任务一直执行失败，不断地重新执行似乎并没有什么意义，只会徒增设备的性能消耗。而随着失败次数的增多，下次重试的时间也应该进行适当的延迟）
     *  第一个参数的可选值有两种，分别是LINEAR和EXPONENTIAL
     *  LINEAR：代表下次重试时间以线性的方式延迟
     *  EXPONENTIAL：代表下次重试时间以指数的方式延迟。
     *
     */
    private fun doOneTimeWork() {
        //OneTimeWorkRequest单次运行的后台任务
        val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInitialDelay(1, TimeUnit.MINUTES) //任务在1分钟后运行
            .addTag("simple") //任务添加标签，可根据该tag去取消任务
            .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(this).enqueue(request)
    }

    private fun doPeriodicWork() {
        //PeriodicWorkRequest构建周期性运行的后台任务请求; 运行周期间隔不能短于15分钟
        val request = PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(request)
    }

    /*-------------------------------------取消后台任务---------------------------------------*/

    /*
     *  可以根据tag或id去取消WorkManager
     *  但是:
     *      使用id只能取消单个后台任务请求
     *      使用标签的话，则可以将同一标签名的所有后台任务请求全部取消，这个功能在逻辑复杂的场景下尤其有用
     */
    private fun cancelWorker() {
        WorkManager.getInstance(this).cancelAllWorkByTag("simple")
    }

    private fun cancelWorker(request: OneTimeWorkRequest) {
        WorkManager.getInstance(this).cancelWorkById(request.id)
    }

    /*-------------------------------------取消后台任务---------------------------------------*/
}