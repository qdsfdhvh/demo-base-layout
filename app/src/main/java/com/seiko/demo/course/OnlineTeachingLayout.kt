package com.seiko.demo.course

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.seiko.demo.ImageLoader
import com.seiko.demo.R
import com.seiko.demo.base.BaseLayout

class OnlineTeachingLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseLayout(context, attrs) {

    // 场景
    var courseType: CourseType = CourseType.DEFAULT
        set(value) {
            if (field != value) {
                field = value
                post { changeChildViewState(value) }
                requestLayout()
            }
        }

    @JvmField
    val teacher = CameraUserLayout(context).autoAddView()

    @JvmField
    val student1 = CameraUserLayout(context).autoAddView()

    @JvmField
    val student2 = CameraUserLayout(context).autoAddView()

    @JvmField
    val student3 = CameraUserLayout(context).autoAddView()

    @JvmField
    val student4 = CameraUserLayout(context).autoAddView()

    @JvmField
    val whiteBoardPublic = View(context).autoAddView()

    @JvmField
    val whiteBoardPrivate = View(context).autoAddView()

    @JvmField
    val whiteBoardUtil = WhiteBoardUtilLayout(context).autoAddView(height = 52.5f.dp)

    @JvmField
    val musicalPreview = ImageView(context).autoAddView(560.5f.dp, 165.5f.dp)

    @JvmField
    val musicalKnock = ImageView(context).autoAddView(560.5f.dp, 155f.dp)

    @JvmField
    val courseMedia = View(context).autoAddView(338.dp)

    private val lineWhiteBoard = View(context).autoAddView(1.dp, 36.dp)

    private val padding = 10.dp

    init {
        ImageLoader.load(teacher.avatar, R.mipmap.ic_default_attend_head)
        ImageLoader.load(student1.avatar, R.mipmap.ic_default_attend_head)
        ImageLoader.load(student2.avatar, R.mipmap.ic_default_attend_head)
        ImageLoader.load(student3.avatar, R.mipmap.ic_default_attend_head)
        ImageLoader.load(student4.avatar, R.mipmap.ic_default_attend_head)
        ImageLoader.load(whiteBoardPublic, Color.WHITE, 6.dp)
        ImageLoader.load(whiteBoardPrivate, Color.WHITE, 6.dp)
        ImageLoader.load(musicalPreview, R.mipmap.ic_attend_musicscore)
        ImageLoader.load(musicalKnock, Color.WHITE, 12.dp)
        ImageLoader.load(courseMedia, "#FED85F", 6.dp)
        teacher.name.text = "老师"
        student1.name.text = "学生1"
        student2.name.text = "学生2"
        student3.name.text = "学生3"
        student4.name.text = "学生4"
        changeChildViewState(courseType)
    }

    private fun changeChildViewState(courseType: CourseType) {
        whiteBoardPrivate.isVisible = courseType == CourseType.WhiteBoard
        whiteBoardPublic.isVisible = courseType == CourseType.WhiteBoard
        whiteBoardUtil.isVisible = courseType == CourseType.WhiteBoard
        lineWhiteBoard.isVisible = courseType == CourseType.WhiteBoard
        musicalPreview.isVisible = courseType == CourseType.Musical
        musicalKnock.isVisible = courseType == CourseType.Musical
        courseMedia.isVisible = courseType == CourseType.CourseWare
        teacher.name.isVisible = (courseType != CourseType.WhiteBoard
                && courseType != CourseType.Musical)
        student1.name.isVisible = (courseType != CourseType.WhiteBoard
                && courseType != CourseType.Musical)
        student2.name.isVisible = courseType == CourseType.Stage
        student3.name.isVisible = courseType == CourseType.Stage
        student4.name.isVisible = courseType == CourseType.Stage
        when (courseType) {
            CourseType.Stage -> {
                teacher.setNameViewHeightAndTextSize(30.dp, 15.sp)
                student1.setNameViewHeightAndTextSize(30.dp, 15.sp)
                student2.setNameViewHeightAndTextSize(17.dp, 11.sp)
                student3.setNameViewHeightAndTextSize(17.dp, 11.sp)
                student4.setNameViewHeightAndTextSize(17.dp, 11.sp)
            }
            CourseType.WhiteBoard -> {
            }
            CourseType.Musical -> {
                teacher.setNameViewHeightAndTextSize(30.dp, 15.sp)
                student1.setNameViewHeightAndTextSize(17.dp, 11.sp)
            }
            CourseType.CourseWare -> {
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        when (courseType) {
            CourseType.Stage -> {
                teacher.measureExactly(282.dp, 208.dp)
                student1.measureExactly(282.dp, 208.dp)
                student2.measureExactly(136.dp, 100.dp)
                student3.measureExactly(136.dp, 100.dp)
                student4.measureExactly(136.dp, 100.dp)
            }
            CourseType.WhiteBoard -> {
                val childHeight = 52.5f.dp
                teacher.measureExactly(71.dp, childHeight)
                if (!whiteBoardUtil.isShowPen) {
                    student1.measureExactly(71.dp, childHeight)
                    student2.measureExactly(71.dp, childHeight)
                    student3.measureExactly(71.dp, childHeight)
                    student4.measureExactly(71.dp, childHeight)
                    whiteBoardPublic.measureExactly(127.5f.dp, childHeight)
                    lineWhiteBoard.autoMeasure()
                }
                whiteBoardPrivate.measureExactly(637.dp, 262.dp)
                whiteBoardUtil.autoMeasure(
                    heightMeasureSpec = childHeight.toExactlyMeasureSpec()
                )
            }
            CourseType.Musical -> {
                teacher.measureExactly(81.5f.dp, 60.5f.dp)
                student1.measureExactly(81.5f.dp, 60.5f.dp)
                student2.measureExactly(81.5f.dp, 60.5f.dp)
                student3.measureExactly(81.5f.dp, 60.5f.dp)
                student4.measureExactly(81.5f.dp, 60.5f.dp)
                musicalPreview.autoMeasure()
                musicalKnock.autoMeasure()
            }
            CourseType.CourseWare -> {
                teacher.measureExactly(245.dp, 181.dp)
                student1.measureExactly(180.dp, 132f.dp)
                student2.measureExactly(55.dp, 42.dp)
                student3.measureExactly(55.dp, 42.dp)
                student4.measureExactly(55.dp, 42.dp)
                courseMedia.autoMeasure()
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        when (courseType) {
            CourseType.Stage -> {
                val horizontalPadding = (measuredWidth
                        - teacher.measuredWidth * 2
                        - padding * 2) / 2

                teacher.layout(horizontalPadding, padding)
                student1.layout(horizontalPadding, padding, true)

                var leftX = (measuredWidth - student2.measuredWidth * 3 - padding * 2) / 2
                val topY = padding + teacher.measuredHeight + padding
                student2.layout(leftX, topY)
                leftX += padding + student2.measuredWidth
                student3.layout(leftX, topY)
                leftX += padding + student3.measuredWidth
                student4.layout(leftX, topY)
            }
            CourseType.WhiteBoard -> {
                val topPadding = 8.5f.dp
                val horizontalPadding = (measuredWidth - whiteBoardPrivate.measuredWidth) / 2

                var startX = horizontalPadding
                teacher.layout(startX, topPadding)

                startX += teacher.measuredWidth + padding
                if (!whiteBoardUtil.isShowPen) {
                    whiteBoardPublic.layout(startX, topPadding)
                    startX += whiteBoardPublic.measuredWidth + 24.5f.dp
                    lineWhiteBoard.layout(
                        startX,
                        topPadding + (whiteBoardPublic.measuredHeight - lineWhiteBoard.measuredHeight) / 2
                    )

                    // 学生摄像头组 从右侧开始绘制
                    startX = horizontalPadding + 65.dp
                    student4.layout(startX, topPadding, true)
                    startX += student4.measuredWidth + padding
                    student3.layout(startX, topPadding, true)
                    startX += student3.measuredWidth + padding
                    student2.layout(startX, topPadding, true)
                    startX += student2.measuredWidth + padding
                    student1.layout(startX, topPadding, true)
                }

                // 白板工具
                whiteBoardUtil.layout(0, 8.5f.dp, true)

                whiteBoardPrivate.layout(
                    horizontalPadding,
                    topPadding + teacher.measuredHeight + 5.dp
                )
            }
            CourseType.Musical -> {
                val smallPadding = 6.5f.dp
                val horizontalPadding = (measuredWidth
                        - teacher.measuredWidth
                        - 7.dp
                        - musicalPreview.measuredWidth) / 2

                var topY = 0
                teacher.layout(horizontalPadding, topY)
                topY += teacher.measuredHeight + smallPadding
                student1.layout(horizontalPadding, topY)
                topY += student1.measuredHeight + smallPadding
                student2.layout(horizontalPadding, topY)
                topY += student2.measuredHeight + smallPadding
                student3.layout(horizontalPadding, topY)
                topY += student3.measuredHeight + smallPadding
                student4.layout(horizontalPadding, topY)

                musicalPreview.layout(horizontalPadding, 0, true)
                musicalKnock.layout(
                    horizontalPadding,
                    musicalPreview.measuredHeight + 7.5f.dp, true
                )
            }
            CourseType.CourseWare -> {
                val smallPadding = 3.dp
                val horizontalPadding = (measuredWidth
                        - teacher.measuredWidth
                        - padding
                        - courseMedia.measuredWidth) / 2

                teacher.layout(horizontalPadding, 0)
                student1.layout(horizontalPadding, teacher.measuredHeight + padding)

                val startX = horizontalPadding + student1.measuredWidth + padding
                var topY = teacher.measuredHeight + padding
                student2.layout(startX, topY)
                topY += student2.measuredHeight + smallPadding
                student3.layout(startX, topY)
                topY += student3.measuredHeight + smallPadding
                student4.layout(startX, topY)

                courseMedia.layout(horizontalPadding, 0, true)
            }
        }
    }
}