package com.fr1014.flow

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.fr1014.extensions.inflate
import com.fr1014.extensions.log
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityFlowBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowActivity : BaseActivity() {

    private val binding by inflate<ActivityFlowBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mapLearning()
    }

    /**
     * 过渡流操作符
     */
    private fun mapLearning() {
        lifecycleScope.launch {
            (1..5).asFlow().map { value ->
                mapNeedTime(value)
            }.collect { value ->
                value.log()
            }
        }
    }

    private suspend fun mapNeedTime(value: Int): String {
        delay(1000)
        return "response: $value"
    }

    /**
     * flowOf 构建器定义了一个发射固定值集的流。
     */
    private fun flowOfLearning() {

        lifecycleScope.launch {

            (1..5).asFlow().collect { value ->
                value.toString().log()
            }

            flowOf(1, 2, 3, 4, 5).collect { value ->
                value.toString().log()
            }
        }
    }

    /**
     *  流是冷的, Flow 是一种类似于序列的冷流, flow 构建器中的代码直到流被收集的时候才运行。流在每次收集的时候启动
     *
     *  Calling simple function...
     *  Calling collect...
     *  Flow started
     *  1
     *  2
     *  3
     *  Calling collect again...
     *  Flow started
     *  1
     *  2
     *  3
     */
    private fun flowLearning() {
        "Calling simple function...".log()
        val flow = simple()
        "Calling collect...".log()

        lifecycleScope.launch {

            flow.collect { value ->
                value.toString().log()
            }

            "Calling collect again...".log()

            flow.collect { value ->
                value.toString().log()
            }
        }
    }

    private fun simple(): Flow<Int> = flow {
        "Flow started".log()

        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }
}