package com.seiko.demo.course.layout

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.seiko.demo.course.R

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

    @JvmField
    val btnShowPen = createBtn(context)

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

    private val padding = 10.dp

    init {
        ImageLoader.load(this, "#FFDC6B", 6.dp)
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
            setMeasuredDimension(
                50.dp.toExactlyMeasureSpec(),
                heightMeasureSpec
            )
            return
        }

        autoMeasures(
            lineShowPen,
            btnRetreat, lineRetreat,
            btnClear, lineClear,
            penColorRed, penColorGreen, penColorOrange, penColorBlue, penColorBlack, linePenColor,
            pen0, pen2, pen4
        )

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val whiteBoardUtilWidth = parentWidth - ((parentWidth - 637.dp) / 2 + 71.dp + 10.dp)
        setMeasuredDimension(
            whiteBoardUtilWidth.toExactlyMeasureSpec(),
            heightMeasureSpec
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var startX = padding
        btnShowPen.layoutVertical(startX, true)
        if (!isShowPen) return

        startX += btnShowPen.measuredWidth + 13.5f.dp
        lineShowPen.layoutVertical(startX, true)
        startX += lineShowPen.measuredWidth + 20.5f.dp
        btnRetreat.layoutVertical(startX, true)
        startX += btnRetreat.measuredWidth + 20.5f.dp
        lineRetreat.layoutVertical(startX, true)
        startX += lineRetreat.measuredWidth + 20.5f.dp
        btnClear.layoutVertical(startX, true)
        startX += btnClear.measuredWidth + 20.5f.dp
        lineClear.layoutVertical(startX, true)

        val penPadding = (measuredWidth
                - 184.dp
                - penColorBlack.measuredWidth * 8
                - lineClear.measuredWidth) / 10

        startX += lineClear.measuredWidth + penPadding
        penColorBlack.layoutVertical(startX, true)
        startX += penColorBlack.measuredWidth + penPadding
        penColorBlue.layoutVertical(startX, true)
        startX += penColorBlue.measuredWidth + penPadding
        penColorGreen.layoutVertical(startX, true)
        startX += penColorGreen.measuredWidth + penPadding
        penColorOrange.layoutVertical(startX, true)
        startX += penColorOrange.measuredWidth + penPadding
        penColorRed.layoutVertical(startX, true)
        startX += penColorRed.measuredWidth + penPadding
        linePenColor.layoutVertical(startX, true)

        startX += linePenColor.measuredWidth + penPadding
        pen4.layoutVertical(startX, true)
        startX += pen4.measuredWidth + penPadding
        pen2.layoutVertical(startX, true)
        startX += pen2.measuredWidth + penPadding
        pen0.layoutVertical(startX, true)
    }

    private fun createBtn(context: Context) =
        ImageView(context).autoAddView(27.5f.dp)

    private fun createPenBtn(context: Context, size: Int) =
        ImageView(context).autoAddView(24.dp) {
            setPadding((24.dp - size) / 2)
        }

    private fun createPenColorBtn(context: Context) =
        ImageView(context).autoAddView(24.dp)

    private fun createLine(context: Context) =
        View(context).autoAddView(0.5f.dp, 30.dp) {
            setBackgroundColor(Color.WHITE)
        }
}