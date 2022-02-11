package com.fr1014.keeplearning

import android.content.Intent
import android.os.Bundle
import com.fr1014.draw.DrawActivity
import com.fr1014.keeplearning.databinding.ActivityMainBinding

class MainActivity : BaseVBActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvDraw.setOnClickListener {
            startActivity(Intent(this, DrawActivity::class.java))
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}