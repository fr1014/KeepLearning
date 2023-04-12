package com.fr1014.cursorverify

import android.os.Bundle
import android.view.View
import com.fr1014.keeplearning.BaseVBActivity
import com.fr1014.keeplearning.databinding.ActivityCursorVerifyBinding

class CursorVerifyActivity : BaseVBActivity<ActivityCursorVerifyBinding>() {

    override fun getViewBinding(): ActivityCursorVerifyBinding =
        ActivityCursorVerifyBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val touchListener = SwipeDismissTouchListener(binding.image, object : SwipeDismissTouchListener.DismissCallbacks {
                override fun canDismiss(token: Any?): Boolean {
                    return true;
                }

                override fun onDismiss(view: View?, token: Any?) {
                    binding.root.removeView(view);
                }
            });
        binding.image.setOnTouchListener(touchListener);
        binding.image.tag = "image-test";
    }
}