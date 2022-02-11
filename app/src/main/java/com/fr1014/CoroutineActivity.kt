package com.fr1014

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.fr1014.keeplearning.BaseVBActivity
import com.fr1014.keeplearning.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.launch

class CoroutineActivity : BaseVBActivity<ActivityCoroutineBinding>() {

    override fun getViewBinding() = ActivityCoroutineBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {

        }
    }
}