package com.fr1014

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.fr1014.keeplearning.BaseVBActivity
import com.fr1014.keeplearning.MainAdapter
import com.fr1014.keeplearning.databinding.ActivityMainBinding

class MainActivity : BaseVBActivity<ActivityMainBinding>() {
    private val tvContentList = ArrayList<String>()

    init {
        tvContentList.apply {
            add("自定义View")
            add("事件分发")
            add("协程(Coroutine)")
            add("ReboundView")
            add("泛型(Generic)")
            add("协程2(Coroutine)")
            add("WorkManager")
            add("协程 + LiveData + ViewModel")
            add("数据流(Flow)")
            add("Fragment相关")
            add("Recyclerview分组悬停")
            add("ClipChildren属性")
            add("Cursor代码验证")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainAdapter = MainAdapter(tvContentList)
        binding.root.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}