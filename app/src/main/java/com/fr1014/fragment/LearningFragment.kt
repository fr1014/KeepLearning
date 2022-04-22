package com.fr1014.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fr1014.extensions.bindView
import com.fr1014.keeplearning.R
import com.fr1014.keeplearning.databinding.FragmentLearningBinding

/**
 * Create by fanrui07
 * Date: 2022/4/20
 * Describe:
 */
class LearningFragment : Fragment(R.layout.fragment_learning) {

    private val binding: FragmentLearningBinding by bindView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}