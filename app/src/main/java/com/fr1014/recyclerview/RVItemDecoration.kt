package com.fr1014.recyclerview

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.MyApplication
import com.fr1014.utils.DimenUtils

/**
 * Create by fanrui07
 * Date: 2022/5/16
 * Describe:
 */
class RVItemDecoration(private val callback: GroupInfoCallBack) : RecyclerView.ItemDecoration() {

    interface GroupInfoCallBack {
        fun getGroupInfo(position: Int): GroupInfo
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val groupInfo = callback.getGroupInfo(position)
        //如果是组内的第一个则将间距撑开为一个Header的高度，或者就是普通的分割线高度
        if (groupInfo.isFirstViewInGroup()) {
            outRect.top = DimenUtils.dp2px(view.context, 50f).toInt()
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount = parent.childCount - 1
        for (index in 0..childCount) {
            val childView = parent.getChildAt(index)   //获取子View
            val position = parent.getChildAdapterPosition(childView) //获取子View在Recyclerview中的position
            
        }
    }
}