package com.fr1014.mixscroll

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityScrollMixBinding

/**
 * 一个竖向滑动的RecyclerView嵌套多个横向滑动的RecyclerView
 * 1. 禁止竖向滑动的RecyclerView中的多指触摸事件，横向RecyclerView一个向左滑、一个向右滑会造成来回震动
 * 2. 多个横向RecyclerView滑动，其中一个RecyclerView滑动时，需要停止其它所以的横向RecyclerView的滑动，否则也会有来回震动的问题
 */
class ScrollMixActivity : BaseActivity() {

    private val binding by inflate<ActivityScrollMixBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = ArrayList<List<String>>()
        for(i in 1..10) {
            val childList = ArrayList<String>()
            childList.add("test1")
            childList.add("test2")
            childList.add("test3")
            childList.add("test4")
            list.add(childList)
        }
        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.verticalRecyclerView.adapter = VerticalAdapter(list)
    }
}