package com.fr1014.webview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.fr1014.MainActivity
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.R
import com.fr1014.keeplearning.databinding.ActivityWebBinding

/**
 * Create by fanrui07
 * Date: 2023/4/12
 * Describe:
 * 测试intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
 * 方式启动的MainActivity打开Scheme中含有key为url的页面
 */
class WebActivity : BaseActivity() {

    private val binding by inflate<ActivityWebBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.jump.setOnClickListener {
            val intent = Intent()
            val host = getString(R.string.app_host)
            val scheme = getString(R.string.app_scheme);
            intent.data = Uri.parse("$scheme://$host/web?autoTitle=false&url=https://piaofang.maoyan.com/feed/news/1869425?language=zh")
            startActivity(intent)
        }
        binding.restart.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            val restartIntent = Intent.makeRestartActivityTask(intent.component)
            startActivity(restartIntent)
        }
    }


}