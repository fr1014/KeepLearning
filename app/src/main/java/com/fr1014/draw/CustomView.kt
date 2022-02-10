package com.fr1014.draw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.fr1014.keeplearning.R

/**
 * Time: 2022/2/9
 * Author: fanrui07
 * Description:
 */
class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint()
    private val path = Path()
    private val rectF = RectF(200f, 200f, 900f, 700f)
    private val radii = floatArrayOf(70f, 70f, 70f, 30f, 30f, 30f, 0f, 0f) //top-left, top-right, bottom-right, bottom-left

    init {
        paint.color = context.resources.getColor(R.color.black)
        //开启抗锯齿
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
//        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.addRoundRect(rectF, radii, Path.Direction.CW)
        canvas?.drawPath(path, paint)
    }
}