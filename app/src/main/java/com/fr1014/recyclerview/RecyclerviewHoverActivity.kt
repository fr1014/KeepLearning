package com.fr1014.recyclerview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityRecyclerviewHorverBinding

/**
 * Create by fanrui07
 * Date: 2022/5/16
 * Describe: Recyclerview分组悬停
 */
class RecyclerviewHoverActivity : BaseActivity() {
    private val binding by inflate<ActivityRecyclerviewHorverBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@RecyclerviewHoverActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(RVItemDecoration(object : RVItemDecoration.GroupInfoCallBack {
                override fun getGroupInfo(position: Int): GroupInfo {
                    val groupId = position / 5
                    val index = position % 5
                    return GroupInfo(groupId, groupId.toString(), index)
                }
            }))
            adapter = RVAdapter()
        }
    }
}