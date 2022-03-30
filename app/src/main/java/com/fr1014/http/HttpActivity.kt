package com.fr1014.http

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityHttpBinding

class HttpActivity : BaseActivity() {

    private val binding by inflate<ActivityHttpBinding>()
    private val viewModel by viewModels<BannerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getBanner()

        viewModel.bannerLiveData.observe(this) {
            it.forEach { banner ->
                val imageView = ImageView(this)
                binding.root.addView(imageView)

                Glide.with(this)
                    .load(banner.imagePath)
                    .into(imageView)
            }
        }
    }
}