package com.fr1014.mixscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 解决多点触控滑动冲突
 */
public class VerticalSingleTouchRecyclerView extends RecyclerView {
    private float startX, startY;

    public VerticalSingleTouchRecyclerView(Context context) {
        super(context);
    }

    public VerticalSingleTouchRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalSingleTouchRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (ev.getPointerCount() > 1) {
//            return true;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

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
                //水平滑动
                boolean isHorizontalScroll = dx > dy;
                if (isHorizontalScroll) {
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

