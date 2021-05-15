package com.seiko.demo.course

import android.app.Activity
import android.app.Application
import android.content.res.Configuration
import com.seiko.autosize.AutoSize
import com.seiko.autosize.AutoSizeConfig
import com.seiko.autosize.callback.AutoAdaptStrategy

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AutoSizeConfig.init(this, MobileAutoAdaptStrategy())
    }
}

private class MobileAutoAdaptStrategy : AutoAdaptStrategy {
    companion object {
        private const val MOBILE_DESIGN_WIDTH_IN_DP = 375f
    }

    override fun apply(target: Any, activity: Activity) {
        val sizeInDp = MOBILE_DESIGN_WIDTH_IN_DP
        val screenSize = if (activity.isVertical()) {
            AutoSizeConfig.screenWidth
        } else {
            AutoSizeConfig.screenHeight
        }
        AutoSize.autoConvertDensity(activity, sizeInDp, screenSize)
    }
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Activity.isVertical(): Boolean {
    return resources.configuration.orientation ==
            Configuration.ORIENTATION_PORTRAIT
}