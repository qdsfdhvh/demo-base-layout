package com.seiko.demo.base

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView

interface BaseLayoutExtensions {

    fun getResources(): Resources

    val View.measureWidthWithMargins get() = measuredWidth + leftMargin + rightMargin
    val View.measureHeightWithMargins get() = measuredHeight + topMargin + bottomMargin

    val View.leftMargin: Int
        get() = (layoutParams as? BaseLayout.LayoutParams)?.leftMargin ?: 0

    val View.topMargin: Int
        get() = (layoutParams as? BaseLayout.LayoutParams)?.topMargin ?: 0

    val View.rightMargin: Int
        get() = (layoutParams as? BaseLayout.LayoutParams)?.rightMargin ?: 0

    val View.bottomMargin: Int
        get() = (layoutParams as? BaseLayout.LayoutParams)?.bottomMargin ?: 0

    val Int.dp: Int
        get() = (this * getResources().displayMetrics.density + 0.5f).toInt()

    val Float.dp: Int
        get() = (this * getResources().displayMetrics.density + 0.5f).toInt()

    val Int.sp: Float
        get() = this * getResources().displayMetrics.scaledDensity * 0.5f

    val Float.sp: Float
        get() = this * getResources().displayMetrics.scaledDensity * 0.5f

    fun Int.toExactlyMeasureSpec(): Int {
        return MeasureSpec.makeMeasureSpec(this, MeasureSpec.EXACTLY)
    }

    fun Int.toAtMostMeasureSpec(): Int {
        return MeasureSpec.makeMeasureSpec(this, MeasureSpec.AT_MOST)
    }

    fun View.defaultWidthMeasureSpec(parentView: ViewGroup): Int = when (layoutParams.width) {
        MATCH_PARENT -> (parentView.measuredWidth - paddingStart - paddingEnd).toExactlyMeasureSpec()
        WRAP_CONTENT -> (parentView.measuredWidth - paddingStart - paddingEnd).toAtMostMeasureSpec()
        0 -> throw IllegalAccessException("Need special treatment for $this")
        else -> layoutParams.width.toExactlyMeasureSpec()
    }

    fun View.defaultHeightMeasureSpec(parentView: ViewGroup): Int = when (layoutParams.height) {
        MATCH_PARENT -> (parentView.measuredHeight - paddingTop - paddingBottom).toExactlyMeasureSpec()
        WRAP_CONTENT -> (parentView.measuredHeight - paddingTop - paddingBottom).toAtMostMeasureSpec()
        0 -> throw IllegalAccessException("Need special treatment for $this")
        else -> layoutParams.height.toExactlyMeasureSpec()
    }

    fun View.measureExactly(width: Int, height: Int) {
        measure(width.toExactlyMeasureSpec(), height.toExactlyMeasureSpec())
    }

    fun View.setPadding(padding: Int) {
        setPadding(padding, padding, padding, padding)
    }

    fun TextView.setTextSizePx(px: Float) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, px)
    }
}