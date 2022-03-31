package com.fr1014.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Create by fanrui07
 * Date: 2022/3/28
 * Describe:
 */
fun String.toastShort(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.log() {
    Log.d("hello", this)
}