package com.fr1014.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.databinding.ActivityFragmentLearningBinding

class FragmentLearningActivity : AppCompatActivity() {
    private val binding by inflate<ActivityFragmentLearningBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragmentById()
    }

    private fun addFragmentById() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, LearningFragment())
            .commitAllowingStateLoss()
    }
}