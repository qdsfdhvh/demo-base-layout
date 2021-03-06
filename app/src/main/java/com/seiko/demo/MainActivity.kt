package com.seiko.demo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.widget.Button
import com.seiko.demo.base.BaseActivity
import com.seiko.demo.base.CustomLayout
import com.seiko.demo.chat.ChatActivity
import com.seiko.demo.course.OnlineActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = MainActivityLayout(this)
        setContentView(contentView)
        contentView.btnGoToOnline.setOnClickListener {
            startActivity(Intent(this, OnlineActivity::class.java))
        }
        contentView.btnGoToChat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }
}

@SuppressLint("SetTextI18n")
class MainActivityLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CustomLayout(context, attrs) {

    @JvmField
    val btnGoToOnline = Button(context).autoAddView()

    @JvmField
    val btnGoToChat = Button(context).autoAddView()

    @JvmField
    val btnTest1 = Button(context).autoAddView()

    @JvmField
    val btnTest2 = Button(context).autoAddView()

    init {
        btnGoToOnline.text = "在线直播"
        btnGoToChat.text = "WindowInsets使用"
        btnTest1.text = "测试1"
        btnTest2.text = "测试2"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        autoMeasure(btnGoToOnline, btnGoToChat, btnTest1, btnTest2)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        layoutCenter(btnGoToOnline, btnGoToChat, btnTest1, btnTest2)
    }
}