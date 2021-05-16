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

    protected fun autoMeasures(vararg views: View) {
        for (view in views) {
            view.autoMeasure()
        }
    }

    protected fun layoutVerticals(startX: Int, vararg views: View) {
        var leftX = startX
        for (view in views) {
            leftX += view.leftMargin
            view.layoutVertical(leftX)
            leftX += view.measuredWidth + view.rightMargin
        }
    }

    protected fun layoutVerticals(vararg views: View) {
        layoutVerticals(0, *views)
    }

    protected fun layoutsHorizontals(startY: Int, vararg views: View) {
        var topY = startY
        for (view in views) {
            topY += view.topMargin
            view.layoutHorizontal(topY)
            topY += view.measuredHeight + view.bottomMargin
        }
    }

    protected fun layoutsHorizontals(vararg views: View) {
        layoutsHorizontals(0, *views)
    }

    protected fun plusWidthWithMargins(vararg views: View): Int {
        return views.sumOf { it.measureWidthWithMargins }
    }

    protected fun plusHeightWithMargins(vararg views: View): Int {
        return views.sumOf { it.measureHeightWithMargins }
    }
}