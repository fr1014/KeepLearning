package com.fr1014

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.fr1014.bean.SchemePortInfo
import com.fr1014.keeplearning.BaseVBActivity
import com.fr1014.keeplearning.MainAdapter
import com.fr1014.keeplearning.R
import com.fr1014.keeplearning.databinding.ActivityMainBinding

class MainActivity : BaseVBActivity<ActivityMainBinding>() {
    private val tvContentList = ArrayList<SchemePortInfo>()

    private fun initPageInfo() {
        tvContentList.apply {
            add(SchemePortInfo("自定义View", getString(R.string.port_activity_draw)))
            add(SchemePortInfo("事件分发", getString(R.string.port_activity_event)))
            add(SchemePortInfo("协程(Coroutine)", getString(R.string.port_activity_coroutine)))
            add(SchemePortInfo("ReboundView", getString(R.string.port_activity_rebound)))
            add(SchemePortInfo("泛型(Generic)", getString(R.string.port_activity_generic)))
            add(SchemePortInfo("协程2(Coroutine)", getString(R.string.port_activity_Coroutine2)))
            add(SchemePortInfo("WorkManager", getString(R.string.port_activity_work_manager)))
            add(SchemePortInfo("协程 + LiveData + ViewModel", getString(R.string.port_activity_http)))
            add(SchemePortInfo("数据流(Flow)", getString(R.string.port_activity_flow)))
            add(SchemePortInfo("Fragment相关", getString(R.string.port_activity_fragment_learning)))
            add(SchemePortInfo("Recyclerview分组悬停", getString(R.string.port_activity_recyclerview_hover)))
            add(SchemePortInfo("ClipChildren属性", getString(R.string.port_activity_clip_children)))
            add(SchemePortInfo("Cursor代码验证", getString(R.string.port_activity_cursor_verify)))
            add(SchemePortInfo("Scheme携带url为key验证", getString(R.string.port_activity_web)))
            add(SchemePortInfo("支持竖向滑动、横向滑动的控件", getString(R.string.port_activity_scroll_mix)))
            add(SchemePortInfo("Activity相关知识点", getString(R.string.port_activity_about)))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("hello", "onCreate: $this")
        super.onCreate(savedInstanceState)

        initPageInfo()
        val mainAdapter = MainAdapter(tvContentList)
        binding.root.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}