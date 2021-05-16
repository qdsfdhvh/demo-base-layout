package com.seiko.demo.course

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.seiko.demo.base.BaseActivity

class OnlineActivity : BaseActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val contentView = OnlineActivityLayout(this)
        setContentView(contentView)
    }
}