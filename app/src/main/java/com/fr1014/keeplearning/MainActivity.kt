package com.fr1014.keeplearning

import android.content.Intent
import android.os.Bundle
import com.fr1014.coroutine.CoroutineActivity
import com.fr1014.draw.DrawActivity
import com.fr1014.keeplearning.databinding.ActivityMainBinding
import com.fr1014.reboundlayout.ReboundViewActivity

class MainActivity : BaseVBActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvDraw.setOnClickListener {
            startActivity(Intent(this, DrawActivity::class.java))
        }

        binding.tvCoroutine.setOnClickListener {
            startActivity(Intent(this, CoroutineActivity::class.java))
        }

        binding.tvReboundView.setOnClickListener {
            startActivity(Intent(this, ReboundViewActivity::class.java))
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}