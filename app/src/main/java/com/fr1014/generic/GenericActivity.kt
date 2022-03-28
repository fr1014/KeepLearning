package com.fr1014.generic

import android.os.Bundle
import android.util.Log
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityGenericBinding

class GenericActivity : BaseActivity() {

    private val binding by inflate<ActivityGenericBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.code.text = "    /**\n" +
                "     * reified 必须与内联函数(inline) 共同使用\n" +
                "     * \n" +
                "     * 在声明泛型的地方必须加上reified关键字来表示该泛型要进行实化，这样声明的泛型不会被类型擦除\n" +
                "     */\n" +
                "    private inline fun <reified T> getGenericType() = T::class.java"
        val result1 = getGenericType<String>()
        val result2 = getGenericType<Int>()
        Log.d("hello", "getGenericType: $result1")
        Log.d("hello", "getGenericType: $result2")
    }

    /**
     * reified 必须与内联函数(inline) 共同使用
     *
     * 在声明泛型的地方必须加上reified关键字来表示该泛型要进行实化，这样声明的泛型不会被类型擦除
     */
    private inline fun <reified T> getGenericType() = T::class.java
}