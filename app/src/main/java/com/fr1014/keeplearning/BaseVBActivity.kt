package com.fr1014.keeplearning

import android.os.Bundle
import android.os.PersistableBundle
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
    }

    abstract fun getViewBinding(): VB
}