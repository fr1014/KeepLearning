package com.fr1014.mixscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Create by fanrui07
 * Date: 2023/5/22
 * Describe:
 */
public class HorizontalRecyclerView extends RecyclerView {
    private float startX, startY;

    public HorizontalRecyclerView(@NonNull Context context) {
        super(context);
    }

    public HorizontalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * requestDisallowInterceptTouchEvent(true)剥夺View对Touch事件的处理权
     */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX = ev.getX();
//                startY = ev.getY();
//                isHorizontalScroll = false;
////                getParent().requestDisallowInterceptTouchEvent(true);
//                Log.d("hello", "onInterceptTouchEvent: MotionEvent.ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float dx = Math.abs(ev.getX() - startX);
//                float dy = Math.abs(ev.getY() - startY);
//                if (dx > dy) {
//                    // 判断为水平滑动
//                    isHorizontalScroll = true;
////                    getParent().requestDisallowInterceptTouchEvent(true);
//                } else {
//                    // 判断为竖直滑动，允许RecyclerView 拦截事件
////                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
//                Log.d("hello", "isHorizontalScroll: " + isHorizontalScroll);
//                break;
////            case MotionEvent.ACTION_UP:
////            case MotionEvent.ACTION_CANCEL:
////                requestDisallowInterceptTouchEvent(false);
////                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

    /*
     * `requestDisallowInterceptTouchEvent()` 是一个方法，用于请求父级View不要拦截当前View的触摸事件。
     * 它的作用是在某个View中，通过调用该方法并传递参数为 `true`，来阻止其父级View拦截该View的触摸事件。
     *
     * 当一个手势触摸事件发生时，Android系统会按照View树结构从顶层向下逐级派发触摸事件。
     * 在这个过程中，父级View有权利拦截子View的触摸事件，以便处理自己的触摸逻辑。
     * 然而，在某些情况下，你可能希望子View能够完全处理触摸事件，而不被父级View拦截。
     *
     * 这时，你可以在子View中调用 `requestDisallowInterceptTouchEvent(true)` 来请求父级View不要拦截该子View的触摸事件。
     * 这样做会告诉父级View，在接下来的触摸事件序列中，不要尝试拦截子View的事件，而是让子View自行处理。
     *
     * 需要注意的是，`requestDisallowInterceptTouchEvent()` 方法只是一个请求，父级View是否真正停止拦截触摸事件，
     * 取决于父级View的实现。一些父级View可能会遵守请求，而另一些可能会忽略该请求并继续拦截触摸事件。
     *
     * 这个方法常用于处理滑动冲突的情况，当你希望某个子View能够自由地处理滑动或触摸事件时，
     * 可以通过调用 `requestDisallowInterceptTouchEvent(true)` 来防止父级View拦截这些事件。
     *
     * 总结一下，`requestDisallowInterceptTouchEvent()` 方法的作用是请求父级View不要拦截当前View的触摸事件，以便让当前View自行处理这些事件。
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(ev.getX() - startX);
                float dy = Math.abs(ev.getY() - startY);
                // 判断为水平滑动
                boolean isHorizontalScroll = dx > dy;
                if (!isHorizontalScroll) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
