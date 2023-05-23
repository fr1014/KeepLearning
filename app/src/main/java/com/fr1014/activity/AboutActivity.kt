package com.fr1014.activity

import android.content.Intent
import android.os.Bundle
import com.fr1014.activity.startmode.AStartModeActivity
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity() {

    private val binding by inflate<ActivityAboutBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvStartMode.setOnClickListener {
            val intent = Intent(this, AStartModeActivity::class.java);
            startActivity(intent)
        }
    }
}