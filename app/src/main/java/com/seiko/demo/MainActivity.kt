package com.seiko.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.widget.Button
import com.seiko.demo.base.BaseActivity
import com.seiko.demo.base.BaseLayout
import com.seiko.demo.course.OnlineActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = MainActivityLayout(this)
        setContentView(contentView)
        contentView.btnGoToOnline.setOnClickListener {
            startActivity(Intent(this, OnlineActivity::class.java))
        }
    }
}

class MainActivityLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseLayout(context, attrs) {

    @JvmField
    val btnGoToOnline = Button(context).autoAddView()

    @JvmField
    val btnGoToMultiType = Button(context).autoAddView()

    @JvmField
    val btnTest1 = Button(context).autoAddView()

    @JvmField
    val btnTest2 = Button(context).autoAddView()

    init {
        btnGoToOnline.text = "在线直播"
        btnGoToMultiType.text = "多类型列表"
        btnTest1.text = "测试1"
        btnTest2.text = "测试2"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        autoMeasure(btnGoToOnline, btnGoToMultiType, btnTest1, btnTest2)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        layoutCenter(btnGoToOnline, btnGoToMultiType, btnTest1, btnTest2)
    }
}