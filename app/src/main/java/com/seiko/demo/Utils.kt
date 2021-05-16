package com.seiko.demo

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

class ImageLoader {
    // ...
    companion object {

        @JvmStatic
        fun load(imageView: ImageView, @DrawableRes resId: Int) {
            imageView.setImageResource(resId)
        }

        @JvmStatic
        fun load(view: View, @DrawableRes resId: Int) {
            view.setBackgroundResource(resId)
        }

        @JvmStatic
        fun load(view: View, @ColorInt color: Int, radius: Int = 0) {
            if (radius == 0) {
                view.setBackgroundColor(color)
            } else {
                view.background = DrawableUtils.createRadiusDrawable(color, radius.toFloat())
            }
        }

        @JvmStatic
        fun load(view: View, colorString: String, radius: Int = 0) {
            load(view, Color.parseColor(colorString), radius)
        }
    }
}

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