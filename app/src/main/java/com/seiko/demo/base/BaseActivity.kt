package com.seiko.demo.base

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.seiko.demo.AdaptScreenUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun getResources(): Resources {
        val rawResources = super.getResources()
        return if (rawResources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            AdaptScreenUtils.adaptWidth(rawResources, MOBILE_DESIGN_WIDTH_IN_DP)
        } else {
            AdaptScreenUtils.adaptHeight(rawResources, MOBILE_DESIGN_WIDTH_IN_DP)
        }
    }

    companion object {
        private const val MOBILE_DESIGN_WIDTH_IN_DP = 375
    }
}