package com.fr1014.draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.fr1014.keeplearning.R;

/**
 * Create by fanrui07
 * Date: 2023/4/13
 * Describe:
 */
public class RoundImageView extends AppCompatImageView {

    private final float[] mRadii = new float[8];
    private Path mPath;
    private RectF mRectF;
    private int mRadius;
    private int mCornerPosition;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPath = new Path();
        mRectF = new RectF();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        mRadius = typedArray.getDimensionPixelSize(R.styleable.RoundImageView_corner_radius, 0);
        mCornerPosition = typedArray.getInt(R.styleable.RoundImageView_corner_position, 0);
        typedArray.recycle();
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        mRectF = new RectF(new Rect(0, 0, r - l, b - t));
        return super.setFrame(l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mRadius > 0) {
            switch (mCornerPosition) {
                case 0:
                    mRadii[0] = mRadius;
                    mRadii[1] = mRadius;
                    mRadii[2] = 0;
                    mRadii[3] = 0;
                    mRadii[4] = 0;
                    mRadii[5] = 0;
                    mRadii[6] = mRadius;
                    mRadii[7] = mRadius;
                    break;
                case 1:
                    mRadii[0] = 0;
                    mRadii[1] = 0;
                    mRadii[2] = mRadius;
                    mRadii[3] = mRadius;
                    mRadii[4] = 0;
                    mRadii[5] = 0;
                    mRadii[6] = mRadius;
                    mRadii[7] = mRadius;
                    break;
                case 2:
                    mRadii[0] = 0;
                    mRadii[1] = 0;
                    mRadii[2] = 0;
                    mRadii[3] = 0;
                    mRadii[4] = mRadius;
                    mRadii[5] = mRadius;
                    mRadii[6] = 0;
                    mRadii[7] = 0;
                    break;
            }
//            mRectF.set(0, 0, getWidth(), getHeight());
            mPath.addRoundRect(mRectF, mRadii, Path.Direction.CW);
            //将路径裁剪到ImageView的区域内
            canvas.clipPath(mPath);
        }
        super.onDraw(canvas);
    }
}
