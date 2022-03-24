package com.fr1014.event

import android.os.Bundle
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityEventBinding

class EventActivity : BaseActivity() {
    private val binding by inflate<ActivityEventBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}