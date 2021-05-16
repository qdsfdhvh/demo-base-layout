package com.seiko.demo.base

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.seiko.demo.AdaptScreenUtils

interface BaseLayoutExtensions {

    companion object {
        const val INVALID_VIEW_SIZE = -3
    }

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
        get() = AdaptScreenUtils.pt2Px(getResources(), this.toFloat())

    val Float.dp: Int
        get() = AdaptScreenUtils.pt2Px(getResources(), this)

    val Int.sp: Float
        get() = AdaptScreenUtils.pt2Px(getResources(), this.toFloat()).toFloat()

    val Float.sp: Float
        get() = AdaptScreenUtils.pt2Px(getResources(), this).toFloat()

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

    fun View.setViewSize(width: Int = INVALID_VIEW_SIZE, height: Int = INVALID_VIEW_SIZE) {
        val mLayoutParams = layoutParams
        if (width != INVALID_VIEW_SIZE) mLayoutParams.width = width
        if (height != INVALID_VIEW_SIZE) mLayoutParams.height = height
        layoutParams = mLayoutParams
    }

    fun View.setViewSize(size: Int) {
        setViewSize(size, size)
    }
}