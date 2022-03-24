package com.fr1014.reboundlayout

import android.graphics.Color
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.fr1014.extensions.inflate
import com.fr1014.keeplearning.BaseActivity
import com.fr1014.keeplearning.databinding.ActivityReboundViewBinding


class ReboundViewActivity : BaseActivity() {

    private val binding by inflate<ActivityReboundViewBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar?.hide()
        //第二参数decorFitsSystemWindows表示是否沉浸，false 表示沉浸，true表示不沉浸
        WindowCompat.setDecorFitsSystemWindows(window, false)
        //设置专栏栏和导航栏的底色，透明
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        //设置沉浸后专栏栏和导航字体的颜色
        ViewCompat.getWindowInsetsController(window.decorView)?.let { controller ->
            controller.isAppearanceLightStatusBars = true  //true: 字黑色 false：默认色
            controller.isAppearanceLightNavigationBars = true
        }

        binding.root.actionUpEvent = { difY ->
            if (difY > 400f) {
                finish()
            }
        }
        binding.ivTest.setOnClickListener {
//            Log.d("hello", "onCreate: ")
        }
    }
}