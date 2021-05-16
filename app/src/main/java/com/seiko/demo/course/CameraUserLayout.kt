package com.seiko.demo.course

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.RectF
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.seiko.demo.ImageLoader
import com.seiko.demo.R
import com.seiko.demo.base.BaseLayout
import com.seiko.demo.base.BaseLayoutExtensions.Companion.INVALID_VIEW_SIZE

class CameraUserLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseLayout(context, attrs) {

    @JvmField
    val camera = FrameLayout(context).autoAddViewMax()

    @JvmField
    val avatar = ImageView(context).autoAddViewMax() {
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    @JvmField
    val name = TextView(context).autoAddView(height = 30.dp) {
        setTextColor(Color.WHITE)
        setTextSizePx(15.sp)
        setPadding(10.dp, 0, 10.dp, 0)
        ellipsize = TextUtils.TruncateAt.END
        maxLines = 1
        gravity = Gravity.CENTER
        it.topMargin = 5.dp
        it.leftMargin = 5.dp
    }

    @JvmField
    val networkQuality = ImageView(context).autoAddView(12.dp) {
        it.leftMargin = 5.dp
        it.bottomMargin = 5.dp
    }

    init {
        ImageLoader.load(name, "#82000000", 15.dp)
        ImageLoader.load(networkQuality, R.mipmap.ic_class_net_good)
    }

    fun setNameViewSize(width: Int = INVALID_VIEW_SIZE, height: Int = INVALID_VIEW_SIZE) {
        name.setViewSize(width, height)
        if (height != INVALID_VIEW_SIZE) {
            ImageLoader.load(name, "#82000000", height / 2)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        camera.autoMeasure()
        avatar.autoMeasure()
        name.autoMeasure()
        networkQuality.autoMeasure()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        camera.layout(0, 0)
        avatar.layout(0, 0)
        name.let { it.layout(it.leftMargin, it.topMargin) }
        networkQuality.let { it.layout(it.leftMargin, it.bottomMargin, fromBottom = true) }
    }

    private val roundPath = Path()
    private val round = 6.dp.toFloat()

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.let {
            roundPath.reset()
            val rectF = RectF(
                paddingLeft.toFloat(),
                paddingTop.toFloat(),
                (measuredWidth - paddingRight).toFloat(),
                (measuredHeight - paddingBottom).toFloat()
            )
            roundPath.addRoundRect(rectF, round, round, Path.Direction.CW)
            it.clipPath(roundPath)

        }
        super.dispatchDraw(canvas)
    }
}