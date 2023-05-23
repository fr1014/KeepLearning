package com.fr1014.activity.startmode

import android.content.Intent
import android.os.Bundle
import com.fr1014.MainActivity
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityStartModeBinding

class BStartModeActivity : BaseActivity() {

    private val binding by inflate<ActivityStartModeBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getModeString()
        binding.tvOther.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.tvStandard.setOnClickListener {
            startModeActivity(STANDARD)
        }
        binding.tvSingleTop.setOnClickListener {
            startModeActivity(SINGLE_TOP)
        }
        binding.tvSingleTask.setOnClickListener {
            startModeActivity(SINGLE_TASK)
        }
        binding.tvSingleInstance.setOnClickListener {
            startModeActivity(SINGLE_INSTANCE)
        }
        binding.tvBStandard.setOnClickListener {
            startBModeActivity(STANDARD)
        }
        binding.tvBSingleTop.setOnClickListener {
            startBModeActivity(SINGLE_TOP)
        }
        binding.tvBSingleTask.setOnClickListener {
            startBModeActivity(SINGLE_TASK)
        }
        binding.tvBSingleInstance.setOnClickListener {
            startBModeActivity(SINGLE_INSTANCE)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        supportActionBar?.title = getModeString()
    }

    private fun startModeActivity(mode: Int) {
        val intent = Intent(this, AStartModeActivity::class.java)
        when (mode) {
            SINGLE_TOP -> {
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            SINGLE_TASK -> {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            SINGLE_INSTANCE -> {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
        startActivity(intent)
    }

    private fun startBModeActivity(mode: Int) {
        val intent = Intent(this, BStartModeActivity::class.java)
        when (mode) {
            SINGLE_TOP -> {
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            SINGLE_TASK -> {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            SINGLE_INSTANCE -> {
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
        startActivity(intent)
    }

    private fun getModeString(): String {
        val flags = intent.flags
//        val isStandardMode = flags and Intent.FLAG_ACTIVITY_NEW_TASK == 0 && flags and Intent.FLAG_ACTIVITY_CLEAR_TASK == 0
        val isSingleTopMode = flags and Intent.FLAG_ACTIVITY_SINGLE_TOP != 0
        if (isSingleTopMode) {
            return "Activity B SingleTop"
        }
        val isSingleTaskMode =
            flags and Intent.FLAG_ACTIVITY_NEW_TASK != 0 && flags and Intent.FLAG_ACTIVITY_CLEAR_TOP != 0
        if (isSingleTaskMode) {
            return "Activity B SingleTask"
        }
        val isSingleInstanceMode =
            flags and Intent.FLAG_ACTIVITY_NEW_TASK != 0 && flags and Intent.FLAG_ACTIVITY_CLEAR_TASK != 0
        if (isSingleInstanceMode) {
            return "Activity B SingleInstance"
        }
        return "Activity B Standard"
    }
}