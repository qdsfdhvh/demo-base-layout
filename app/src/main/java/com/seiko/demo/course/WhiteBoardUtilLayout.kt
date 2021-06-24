package com.seiko.demo.course

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.seiko.demo.utils.DrawableUtils
import com.seiko.demo.utils.ImageLoader
import com.seiko.demo.R
import com.seiko.demo.base.BaseLayout

class WhiteBoardUtilLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseLayout(context, attrs) {

    var isShowPen = false
        private set(value) {
            if (field != value) {
                field = value
                post { changeChildViewState() }
                requestLayout()
            }
        }

    private val btnSize = 27.5f.dp
    private val btnMargin = 20.5f.dp
    private val btnPenSize = 24.dp
    private val lineWidth = 0.5f.dp

    @JvmField
    val btnShowPen = ImageView(context).autoAddView(btnSize)

    @JvmField
    val btnRetreat = createBtn(context)

    @JvmField
    val btnClear = createBtn(context)

    @JvmField
    val penColorRed = createPenColorBtn(context)

    @JvmField
    val penColorGreen = createPenColorBtn(context)

    @JvmField
    val penColorOrange = createPenColorBtn(context)

    @JvmField
    val penColorBlue = createPenColorBtn(context)

    @JvmField
    val penColorBlack = createPenColorBtn(context)

    @JvmField
    val pen0 = createPenBtn(context, 10.dp)

    @JvmField
    val pen2 = createPenBtn(context, 15.dp)

    @JvmField
    val pen4 = createPenBtn(context, 20.dp)

    private val lineShowPen = createLine(context)
    private val lineRetreat = createLine(context)
    private val lineClear = createLine(context)
    private val linePenColor = createLine(context)

    // ↓ 以下View的间距为动态算出
    private val toolViewsWithOutBtn = arrayOf(
        pen0, pen2, pen4, linePenColor,
        penColorRed, penColorOrange, penColorGreen, penColorBlue, penColorBlack, lineClear
    )

    private val toolViews = toolViewsWithOutBtn + arrayOf(
        btnClear, lineRetreat,
        btnRetreat, lineShowPen
    )

    init {
        background = DrawableUtils.createRadiusDrawable(
            Color.parseColor("#FFDC6B"),
            radiusTopLeft = 6.dp.toFloat(),
            radiusBottomLeft = 6.dp.toFloat()
        )
        ImageLoader.load(btnShowPen, R.mipmap.whiteboard_pen)
        ImageLoader.load(btnRetreat, R.mipmap.whiteboard_retreat)
        ImageLoader.load(btnClear, R.mipmap.whiteboard_clear)
        ImageLoader.load(penColorRed, R.drawable.select_board_red)
        ImageLoader.load(penColorGreen, R.drawable.select_board_green)
        ImageLoader.load(penColorOrange, R.drawable.select_board_orange)
        ImageLoader.load(penColorBlue, R.drawable.select_board_blue)
        ImageLoader.load(penColorBlack, R.drawable.select_board_black)
        ImageLoader.load(pen0, R.drawable.select_boardpen1)
        ImageLoader.load(pen2, R.drawable.select_boardpen2)
        ImageLoader.load(pen4, R.drawable.select_boardpen4)

        pen0.isSelected = true
        penColorBlack.isSelected = true

        btnShowPen.setOnClickListener {
            isShowPen = !isShowPen
        }
    }

    private fun changeChildViewState() {
        lineShowPen.isVisible = isShowPen
        lineRetreat.isVisible = isShowPen
        lineClear.isVisible = isShowPen
        linePenColor.isVisible = isShowPen
        btnRetreat.isVisible = isShowPen
        btnClear.isVisible = isShowPen
        penColorRed.isVisible = isShowPen
        penColorGreen.isVisible = isShowPen
        penColorOrange.isVisible = isShowPen
        penColorBlue.isVisible = isShowPen
        penColorBlack.isVisible = isShowPen
        pen0.isVisible = isShowPen
        pen2.isVisible = isShowPen
        pen4.isVisible = isShowPen
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        btnShowPen.autoMeasure()
        if (!isShowPen) {
            return
        }

        autoMeasure(*toolViews)

        // 算出pen之间的间距
        val whiteBoardUtilWidth = MeasureSpec.getSize(widthMeasureSpec)
        val penPadding = (whiteBoardUtilWidth
                - 50.dp - btnMargin * 4 - btnPenSize * 10 - lineWidth * 4) / 10
        toolViewsWithOutBtn.setMargins(left = penPadding)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        btnShowPen.layoutHorizontal(10.dp, true)
        if (!isShowPen) {
            return
        }
        layoutHorizontal(*toolViews)
    }

    private fun createBtn(context: Context) =
        ImageView(context).autoAddView(btnSize) {
            it.leftMargin = btnMargin
            it.rightMargin = btnMargin
        }

    private fun createPenBtn(context: Context, size: Int) =
        ImageView(context).autoAddView(btnPenSize) {
            setPadding((btnPenSize - size) / 2)
        }

    private fun createPenColorBtn(context: Context) =
        ImageView(context).autoAddView(btnPenSize)

    private fun createLine(context: Context) =
        View(context).autoAddView(lineWidth, 30.dp) {
            setBackgroundColor(Color.WHITE)
        }
}