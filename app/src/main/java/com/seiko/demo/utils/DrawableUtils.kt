package com.seiko.demo.utils

import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorInt

object DrawableUtils {

    @JvmStatic
    fun createRadiusDrawable(
        @ColorInt color: Int,
        radiusTopLeft: Float = 0f,
        radiusTopRight: Float = 0f,
        radiusBottomRight: Float = 0f,
        radiusBottomLeft: Float = 0f
    ) = GradientDrawable().apply {
        setColor(color)
        cornerRadii = floatArrayOf(
            radiusTopLeft, radiusTopLeft,
            radiusTopRight, radiusTopRight,
            radiusBottomRight, radiusBottomRight,
            radiusBottomLeft, radiusBottomLeft
        )
    }

    @JvmStatic
    fun createRadiusDrawable(@ColorInt color: Int, radius: Float) =
        createRadiusDrawable(color, radius, radius, radius, radius)
}