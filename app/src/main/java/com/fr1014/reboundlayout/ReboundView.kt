package com.fr1014.reboundlayout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

/**
 * Create by fanrui07
 * Date: 2022/3/3
 * Describe:
 */
class ReboundView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private var mDownX = 0f
    private var mDownY = 0f
    private var innerView: View? = null
    var isIntercept = false
    var actionUpEvent: (difY: Float) -> Unit = {}

    /**
     * 需要有 (子View & 子View设置点击事件) 才会有 ACTION_MOVE 和 ACTION_UP 事件
     * 若没有子View仅会有 ACTION_DOWN 事件
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        innerView?.let {
            when(ev?.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    mDownX = ev.x
                    mDownY = ev.y
                }
                MotionEvent.ACTION_MOVE -> {
                    var parent = parent
                    //禁止所有父View拦截触摸事件的功能
                    while (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true)
                        parent = parent.parent
                        isIntercept = true
                    }
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    //恢复所有父布局禁用拦截事件的功能
                    if (isIntercept) {
                        var parent = parent
                        while (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(false)
                            parent = parent.parent
                        }
                        isIntercept = false
                    }
                    mDownX = 0f
                    mDownY = 0f
                }
                else -> {}
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        innerView?.let { _innerView ->
            when(event?.actionMasked) {
                MotionEvent.ACTION_MOVE -> {
                    val difY = event.y - mDownY

                    if (difY < 0) return super.onTouchEvent(event)  //difY < 0，视图上滑无需处理

                    //canScrollVertically(-1) 检查视图是否可以在垂直方向向下滚动
                    if (!_innerView.canScrollVertically(-1) && difY > 0) {  //视图可以向下滚动
                        _innerView.translationY = difY   //View根据手势滑动的difY移动
                        return true
                    }
                }
                MotionEvent.ACTION_UP -> {
                    performClick()
                    val difY = _innerView.translationY
                    if (difY != 0f) { //ACTION_UP时，View不在原位置时，执行动画移动回原位
                        _innerView.animate().translationY(0f).duration = 300
                    }
                    actionUpEvent.invoke(difY)
                }
                else -> {}
            }
        }
        return super.onTouchEvent(event)
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 0) {
            innerView = getChildAt(0)
        } else {
            throw IllegalArgumentException("必须拥有一个子View，与ScrollView类似")
        }
    }
}