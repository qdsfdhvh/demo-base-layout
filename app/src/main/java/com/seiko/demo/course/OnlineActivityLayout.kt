package com.seiko.demo.course

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import com.seiko.demo.ImageLoader
import com.seiko.demo.R
import com.seiko.demo.base.BaseLayout

class OnlineActivityLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseLayout(context, attrs) {

    @JvmField
    val topLayout = OnlineTopLayout(context).autoAddViewMax(height = 37.dp)

    @JvmField
    val teachingLayout = OnlineTeachingLayout(context).autoAddViewMax(height = 338.dp)

    private val btnChange = TextView(context).autoAddView(40.dp) {
        setBackgroundColor(Color.BLUE)
        setTextColor(Color.WHITE)
        gravity = Gravity.CENTER
        text = "S"
    }

    init {
        ImageLoader.load(this, R.mipmap.bg_attend_class)

        val courseTypes = arrayOf(
            CourseType.Stage,
            CourseType.WhiteBoard,
            CourseType.Musical,
            CourseType.CourseWare
        )
        var index = 1
        btnChange.setOnClickListener {
            teachingLayout.courseType = courseTypes[index++]
            if (index >= 4) index = 0
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        topLayout.autoMeasure()
        teachingLayout.autoMeasure()
        btnChange.autoMeasure()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        layoutsHorizontals(topLayout, teachingLayout)
        btnChange.layout(0, 0, fromBottom = true)
    }
}