package com.fr1014.draw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fr1014.keeplearning.R

class DrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "自定义View"
        setContentView(R.layout.activity_draw)
    }

}