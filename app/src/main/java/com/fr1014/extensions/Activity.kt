package com.fr1014.extensions

import android.app.Activity
import android.content.Intent

/**
 * Create by fanrui07
 * Date: 2022/3/24
 * Describe:
 */
inline fun <reified T : Activity> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T : Activity> Activity.startActivity(block: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.block()
    startActivity(intent)
}