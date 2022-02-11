package com.fr1014.keeplearning

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Time: 2022/2/10
 * Author: fanrui07
 * Description:
 */
abstract class BaseVBActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        Toast.makeText(this, javaClass.simpleName, Toast.LENGTH_SHORT).show() //弹出当前的Activity名
    }

    abstract fun getViewBinding(): VB
}