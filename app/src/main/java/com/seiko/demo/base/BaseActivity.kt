package com.seiko.demo.base

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.seiko.demo.AdaptScreenUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun getResources(): Resources {
        return AdaptScreenUtils.adaptHeight(super.getResources(), MOBILE_DESIGN_WIDTH_IN_DP)
    }

    companion object {
        private const val MOBILE_DESIGN_WIDTH_IN_DP = 375
    }
}