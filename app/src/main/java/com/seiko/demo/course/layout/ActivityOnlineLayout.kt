package com.seiko.demo.course.layout

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet

class ActivityOnlineLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseLayout(context, attrs) {

    @JvmField
    val topLayout = OnlineTopLayout(context).autoAddViewMax(height = 37.dp)

    @JvmField
    val teachingLayout = OnlineTeachingLayout(context).autoAddViewMax(height = 338.dp) {
        setBackgroundColor(Color.RED)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        topLayout.autoMeasure()
        teachingLayout.autoMeasure()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        layoutsHorizontals(topLayout, teachingLayout)
    }
}