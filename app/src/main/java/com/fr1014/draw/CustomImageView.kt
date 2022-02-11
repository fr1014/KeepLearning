package com.fr1014.draw

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.fr1014.keeplearning.R

/**
 * Time: 2022/2/9
 * Author: fanrui07
 * Description: 自定义ImageView，支持添加边框和设置圆角
 */
class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    private var mBoundsF: RectF? = null
    private val maskerPaint = Paint()
    private val boundsPaint = Paint()
    private val boundsPath = Path()
    private val borderPaint = Paint()
    private val borderPath = Path()  //边框
    var topLeftRadius = true
    var topRightRadius = true
    var bottomRightRadius = true
    var bottomLeftRadius = true
    private val radii = FloatArray(8) //top-left, top-right, bottom-right, bottom-left
    var radius = 0f   //图片圆角弧度

    init {

        context.obtainStyledAttributes(attrs, R.styleable.CustomImageView).apply {
            topLeftRadius = getBoolean(R.styleable.CustomImageView_top_left, true)
            topRightRadius = getBoolean(R.styleable.CustomImageView_top_right, true)
            bottomRightRadius = getBoolean(R.styleable.CustomImageView_bottom_right, true)
            bottomLeftRadius = getBoolean(R.styleable.CustomImageView_bottom_left, true)
            radius = getDimension(R.styleable.CustomImageView_radius, 0f)
            recycle()
        }

        if (topLeftRadius) {
            radii[0] = radius
            radii[1] = radius
        }
        if (topRightRadius) {
            radii[2] = radius
            radii[3] = radius
        }
        if (bottomRightRadius) {
            radii[4] = radius
            radii[5] = radius
        }
        if (bottomLeftRadius) {
            radii[6] = radius
            radii[7] = radius
        }
        maskerPaint.isAntiAlias = true
        maskerPaint.style = Paint.Style.FILL

        boundsPaint.isAntiAlias = true
        boundsPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        borderPaint.color = context.resources.getColor(R.color.black)
        //开启抗锯齿
        borderPaint.isAntiAlias = true
        borderPaint.style = Paint.Style.STROKE
    }

    override fun setFrame(l: Int, t: Int, r: Int, b: Int): Boolean {
        mBoundsF = RectF(Rect(0, 0, r - l, b - t));
        return super.setFrame(l, t, r, b)
    }

    override fun onDraw(canvas: Canvas?) {
        val sc = canvas?.saveLayer(mBoundsF, null) ?: 0
        //---------------图片圆角------------------//
        mBoundsF?.let {
            boundsPath.addRoundRect(it, radii, Path.Direction.CW)
        }
        canvas?.drawPath(boundsPath, maskerPaint)
        //---------------图片圆角------------------//

        //保存遮罩+view的背景
        canvas?.saveLayer(mBoundsF, boundsPaint);
        super.onDraw(canvas)
        //恢复
        canvas?.restoreToCount(sc);

        //---------------图片边框绘制-----------------//
        mBoundsF?.let {
            borderPath.addRoundRect(it, radii, Path.Direction.CW)
        }
        canvas?.drawPath(borderPath, borderPaint)
        //---------------图片边框绘制----------------//
    }
}