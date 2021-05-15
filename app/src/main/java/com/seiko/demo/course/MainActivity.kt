package com.seiko.demo.course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import com.seiko.demo.course.layout.ActivityOnlineLayout

class MainActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        val contentView = ActivityOnlineLayout(this)
        setContentView(contentView)
    }
}