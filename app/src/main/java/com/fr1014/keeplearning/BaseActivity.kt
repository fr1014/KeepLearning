package com.fr1014.keeplearning

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Time: 2022/3/17
 * Author: fanrui07
 * Description:
 */
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("hello", "onCreate: $this")
        super.onCreate(savedInstanceState)
        supportActionBar?.title = javaClass.simpleName
        Toast.makeText(this, javaClass.simpleName, Toast.LENGTH_SHORT).show() //弹出当前的Activity名
    }

    override fun onStart() {
        Log.d("hello", "onStart: $this")
        super.onStart()
    }

    override fun onRestart() {
        Log.d("hello", "onRestart: $this")
        super.onRestart()
    }

    override fun onNewIntent(intent: Intent?) {
        Log.d("hello", "onNewIntent: $this")
        super.onNewIntent(intent)
    }

    override fun onResume() {
        Log.d("hello", "onResume: $this")
        super.onResume()
    }

    override fun onPause() {
        Log.d("hello", "onPause: $this")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("hello", "onDestroy: $this")
        super.onDestroy()
    }
}