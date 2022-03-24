package com.fr1014.generic

import android.os.Bundle
import android.util.Log
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityCoroutineBinding

class GenericActivity : BaseActivity() {

    private val binding by inflate<ActivityCoroutineBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val result1 = getGenericType<String>()
        val result2 = getGenericType<Int>()
        Log.d("hello", "getGenericType: $result1")
        Log.d("hello", "getGenericType: $result2")
    }

    private inline fun <reified T> getGenericType() = T::class.java
}