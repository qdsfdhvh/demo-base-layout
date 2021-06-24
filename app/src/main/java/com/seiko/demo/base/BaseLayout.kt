package com.seiko.demo.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams.MATCH_PARENT
import android.view.ViewGroup.MarginLayoutParams.WRAP_CONTENT

abstract class BaseLayout(
    context: Context,
    attrs: AttributeSet? = null
) : ViewGroup(context, attrs), BaseLayoutExtensions {

    protected fun View.autoMeasure(
        widthMeasureSpec: Int = defaultWidthMeasureSpec(parentView = this@BaseLayout),
        heightMeasureSpec: Int = defaultHeightMeasureSpec(parentView = this@BaseLayout)
    ) {
        measure(widthMeasureSpec, heightMeasureSpec)
    }

    protected fun Array<View>.autoMeasure() {
        forEach { it.autoMeasure() }
    }

    protected fun View.layout(x: Int, y: Int) = layout(
        x, y, x + measuredWidth, y + measuredHeight
    )

    protected fun View.layout(
        x: Int, y: Int,
        fromRight: Boolean = false,
        fromBottom: Boolean = false
    ) = layout(
        if (fromRight) this@BaseLayout.measuredWidth - x - measuredWidth else x,
        if (fromBottom) this@BaseLayout.measuredHeight - y - measuredHeight else y
    )

    protected fun View.layoutCenter() = layout(
        (this@BaseLayout.measuredWidth - measuredWidth) / 2,
        (this@BaseLayout.measuredHeight - measuredHeight) / 2
    )

    protected fun View.layoutVertical(x: Int, fromRight: Boolean = false) = layout(
        x = x,
        y = (this@BaseLayout.measuredHeight - measuredHeight) / 2,
        fromRight = fromRight
    )

    protected fun View.layoutHorizontal(y: Int, fromBottom: Boolean = false) = layout(
        x = (this@BaseLayout.measuredWidth - measuredWidth) / 2,
        y = y,
        fromBottom = fromBottom
    )

    protected fun <T : View> T.autoAddView(
        width: Int = WRAP_CONTENT,
        height: Int = WRAP_CONTENT,
        block: T.(LayoutParams) -> Unit = {}
    ): T = apply {
        val mLayoutParams = LayoutParams(width, height)
        block(mLayoutParams)
        layoutParams = mLayoutParams
        this@BaseLayout.addView(this)
    }

    protected fun <T : View> T.autoAddView(
        size: Int,
        block: T.(LayoutParams) -> Unit = {}
    ) = autoAddView(size, size, block)

    protected fun <T : View> T.autoAddViewMax(
        width: Int = MATCH_PARENT,
        height: Int = MATCH_PARENT,
        block: T.(LayoutParams) -> Unit = {}
    ) = autoAddView(width, height, block)

    class LayoutParams : MarginLayoutParams {
        constructor(size: Int) : super(size, size)
        constructor(width: Int = WRAP_CONTENT, height: Int = WRAP_CONTENT) : super(width, height)
    }

    protected fun layoutCenter(vararg views: View) {
        var topY = (measuredHeight - views.sumOf { it.measureHeightWithMargins }) / 2
        for (view in views) {
            view.layoutHorizontal(topY)
            topY += view.measureHeightWithMargins
        }
    }

    protected fun layoutVerticals(vararg views: View): Int {
        return layoutVerticals(0, *views)
    }

    protected fun layoutVerticals(startX: Int, vararg views: View): Int {
        var leftX = startX
        for (view in views) {
            leftX += view.leftMargin
            view.layoutVertical(leftX)
            leftX += view.measuredWidth + view.rightMargin
        }
        return leftX
    }

    protected fun layoutVerticals(startX: Int, centerY: Int, padding: Int, vararg views: View): Int {
        var leftX = startX
        for (view in views) {
            leftX += view.leftMargin + padding
            view.layout(leftX, centerY - view.measuredHeight / 2)
            leftX += view.measuredWidth + view.rightMargin
        }
        return leftX
    }

    protected fun layoutsHorizontals(vararg views: View): Int {
        return layoutsHorizontals(0, *views)
    }

    protected fun layoutsHorizontals(startY: Int, vararg views: View): Int {
        var topY = startY
        for (view in views) {
            topY += view.topMargin
            view.layoutHorizontal(topY)
            topY += view.measuredHeight + view.bottomMargin
        }
        return topY
    }

    protected fun layoutsHorizontals(startY: Int, centerX: Int, vararg views: View): Int {
        var topY = startY
        for (view in views) {
            topY += view.topMargin
            view.layout(centerX - view.measuredWidth / 2, topY)
            topY += view.measuredHeight + view.bottomMargin
        }
        return topY
    }

    protected fun plusWidthWithMargins(vararg views: View): Int {
        return views.sumOf { it.measureWidthWithMargins }
    }

    protected fun plusHeightWithMargins(vararg views: View): Int {
        return views.sumOf { it.measureHeightWithMargins }
    }
}