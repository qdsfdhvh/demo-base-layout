package com.seiko.demo.base

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.seiko.demo.AdaptScreenUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptHeight(super.getResources(), MOBILE_DESIGN_WIDTH_IN_DP)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Activity.isVertical(): Boolean {
        return resources.configuration.orientation ==
                Configuration.ORIENTATION_PORTRAIT
    }

    companion object {
        private const val MOBILE_DESIGN_WIDTH_IN_DP = 375
    }
}