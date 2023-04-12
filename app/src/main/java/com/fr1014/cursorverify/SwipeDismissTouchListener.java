package com.fr1014.cursorverify;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;

/**
 * Create by fanrui07
 * Date: 2023/3/21
 * Describe: 滑动消失的View
 */
public class SwipeDismissTouchListener implements View.OnTouchListener {
    private int mSlop; // 滑动距离的阈值
    private int mMinFlingVelocity; // 最小滑动速度
    private int mMaxFlingVelocity; // 最大滑动速度
    private long mAnimationTime; // 动画时间

    private View mView; // 被滑动的View
    private DismissCallbacks mCallbacks; // 回调接口
    private int mViewWidth = 1; // View的宽度，防止除0

    private float mDownX; // 按下时的X坐标
    private float mDownY; // 按下时的Y坐标
    private boolean mSwiping; // 是否正在滑动
    private int mSwipingSlop; // 滑动距离的阈值
    /**
     * The velocity tracker used to determine the speed of the swipe.
     */
    private VelocityTracker mVelocityTracker; // 速度追踪器
    private float mTranslationX; // X轴方向的偏移量

    private boolean mPaused; // 是否暂停

    /**
     * The callback interface used by {@link SwipeDismissTouchListener} to inform its client
     * about a successful dismissal of the view for which it was created.
     */
    public interface DismissCallbacks {
        /**
         * Called to determine whether the view can be dismissed.
         */
        boolean canDismiss(Object token);

        /**
         * Called when the user has indicated they she would like to dismiss the view.
         *
         * @param view  The originating {@link View} to be dismissed.
         * @param token The optional token passed to this object's constructor.
         */
        void onDismiss(View view, Object token);
    }

    /**
     * Constructs a new swipe-to-dismiss touch listener for the given view.
     *
     * @param view     The view to make dismissable.
     * @param callbacks The callback to trigger when the user has indicated that she would like to
     *                  dismiss this view.
     */
    public SwipeDismissTouchListener(View view, DismissCallbacks callbacks) {
        ViewConfiguration vc = ViewConfiguration.get(view.getContext());
        mSlop = vc.getScaledTouchSlop(); // 获取滑动距离的阈值
        mMinFlingVelocity = vc.getScaledMinimumFlingVelocity() * 16; // 获取最小滑动速度
        mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity(); // 获取最大滑动速度
        mAnimationTime = view.getContext().getResources().getInteger(
                android.R.integer.config_shortAnimTime); // 获取动画时间
        mView = view; // 被滑动的View
        mCallbacks = callbacks; // 回调接口
    }

    /**
     * Enables or disables (pauses or resumes) watching for swipe-to-dismiss gestures.
     *
     * @param enabled Whether or not to watch for gestures.
     */
    public void setEnabled(boolean enabled) {
        mPaused = !enabled; // 是否暂停
    }

    /**
     * Returns an {@link android.widget.AbsListView.OnScrollListener}    /**
     * Returns an {@link android.widget.AbsListView.OnScrollListener} to be added to the {@link
     * android.widget.ListView} using {@link android.widget.ListView#setOnScrollListener(android.widget.AbsListView.OnScrollListener)}.
     * If the {@link SwipeDismissTouchListener} is being used to dismiss items for a {@link
     * android.widget.ListView} then this scroll listener must be used to prevent conflicts with
     * ListView's own scroll listener.
     *
     * @see SwipeDismissListViewTouchListener
     */
    public AbsListView.OnScrollListener makeScrollListener() {
        return new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                setEnabled(scrollState != AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL);
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        };
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (mViewWidth < 2) {
            mViewWidth = mView.getWidth(); // 获取View的宽度
        }

        switch (motionEvent.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                if (mPaused) {
                    return false;
                }

                // TODO: ensure this is a finger, and set a flag
                mDownX = motionEvent.getRawX(); // 获取按下时的X坐标
                mDownY = motionEvent.getRawY(); // 获取按下时的Y坐标
                mVelocityTracker = VelocityTracker.obtain(); // 获取速度追踪器
                mVelocityTracker.addMovement(motionEvent); // 添加事件
                return true;
            }

            case MotionEvent.ACTION_UP: {
                if (mVelocityTracker == null) {
                    break;
                }

                float deltaX = motionEvent.getRawX() - mDownX; // 获取X轴方向的偏移量
                mVelocityTracker.addMovement(motionEvent); // 添加事件
                mVelocityTracker.computeCurrentVelocity(1000); // 计算速度
                float velocityX = mVelocityTracker.getXVelocity(); // 获取X轴方向的速度
                float absVelocityX = Math.abs(velocityX); // 获取X轴方向速度的绝对值
                float absVelocityY = Math.abs(mVelocityTracker.getYVelocity()); // 获取Y轴方向速度的绝对值
                boolean dismiss = false; // 是否可以滑动消失
                boolean dismissRight = false; // 是否向右滑动消失
                if (Math.abs(deltaX) > mViewWidth / 2 && mSwiping) { // 如果滑动距离大于View宽度的一半且正在滑动
                    dismiss = true; // 可以滑动消失
                    dismissRight = deltaX > 0; // 如果滑动距离大于0，向右滑动消失
                } else if (mMinFlingVelocity <= absVelocityX && absVelocityX <= mMaxFlingVelocity
                        && absVelocityY < absVelocityX && mSwiping) { // 如果速度大于最小速度，小于最大速度，且X轴方向速度大于Y轴方向速度，且正在滑动
                    // dismiss only if flinging in the same direction as dragging
                    dismiss = (velocityX < 0) == (deltaX < 0); // 如果速度方向和滑动方向一致，可以滑动消失
                    dismissRight = mVelocityTracker.getXVelocity() > 0; // 如果速度大于0，向右滑动消失
                }
                if (dismiss) {
                    // dismiss
                    final View tmpView = mView; // for access in run()
                    final Object token = mView.getTag(); // 获取tag
                    ++mViewWidth; // 防止动画被取消
                    mView.animate()
                            .translationX(dismissRight ? mViewWidth : -mViewWidth) // 设置X轴方向的偏移量
                            .alpha(0) // 设置透明度
                            .setDuration(mAnimationTime) // 设置动画时间
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    mCallbacks.onDismiss(tmpView, token); // 回调
                                    // Reset view presentation
                                    tmpView.setAlpha(1); // 重置透明度
                                    tmpView.setTranslationX(0); // 重置X轴方向的偏移量
                                    --mViewWidth; // 防止动画被取消
                                }
                            });
                    mVelocityTracker.recycle(); // 回收速度追踪器
                    mVelocityTracker = null;
                } else if (mSwiping) {
                    // cancel
                    mView.animate()
                            .translationX(0) // 重置X轴方向的偏移量
                            .alpha(1) // 重置透明度
                            .setDuration(mAnimationTime) // 设置动画时间
                            .setListener(null);
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle(); // 回收速度追踪器
                }
                mVelocityTracker = null;
                mDownX = 0; // 重置按下时的X坐标
                mDownY = 0; // 重置按下时的Y坐标
                mSwiping = false; // 重置是否正在滑动
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (mVelocityTracker == null || mPaused) {
                    break;
                }

                mVelocityTracker.addMovement(motionEvent); // 添加事件
                float deltaX = motionEvent.getRawX() - mDownX; // 获取X轴方向的偏移量
                float deltaY = motionEvent.getRawY() - mDownY; // 获取Y轴方向的偏移量
                if (Math.abs(deltaX) > mSlop && Math.abs(deltaY) < Math.abs(deltaX) / 2) { // 如果滑动距离大于最小滑动距离，且Y轴方向的偏移量小于X轴方向的偏移量的一半
                    mSwiping = true; // 正在滑动
                    mSwipingSlop = (deltaX > 0 ? mSlop : -mSlop); // 设置滑动距离
                    mView.getParent().requestDisallowInterceptTouchEvent(true); // 禁止父View拦截事件

                    // Cancel listview's touch
                    MotionEvent cancelEvent = MotionEvent.obtain(motionEvent);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL |
                            (motionEvent.getActionIndex()
                                    << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
                    mView.onTouchEvent(cancelEvent); // 取消ListView的触摸事件
                    cancelEvent.recycle(); // 回收事件
                }

                if (mSwiping) {
                    mTranslationX = deltaX;
                    mView.setTranslationX(deltaX - mSwipingSlop); // 设置X轴方向的偏移量
                    mView.setAlpha(Math.max(0f, Math.min(1f,
                            1f - 2f * Math.abs(deltaX) / mViewWidth))); // 设置透明度
                    return true;
                }
                break;
            }
        }
        return false;
    }
}

