package com.fr1014.utils

import android.content.Context

/**
 * Create by fanrui07
 * Date: 2022/3/24
 * Describe:
 */
object DimenUtils {

    fun dp2px(context: Context, dp: Float): Float = dp * context.resources.displayMetrics.density

    fun px2dp(context: Context, px: Float): Float = px / context.resources.displayMetrics.density
}