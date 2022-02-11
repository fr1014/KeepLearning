package com.fr1014.draw

import android.os.Bundle
import com.fr1014.keeplearning.BaseVBActivity
import com.fr1014.keeplearning.databinding.ActivityDrawBinding

class DrawActivity : BaseVBActivity<ActivityDrawBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "自定义View"
    }

    override fun getViewBinding() =  ActivityDrawBinding.inflate(layoutInflater)

}