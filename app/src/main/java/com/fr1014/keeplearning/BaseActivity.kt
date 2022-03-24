package com.fr1014.keeplearning

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Time: 2022/3/17
 * Author: fanrui07
 * Description:
 */
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = javaClass.simpleName
        Toast.makeText(this, javaClass.simpleName, Toast.LENGTH_SHORT).show() //弹出当前的Activity名
    }

}