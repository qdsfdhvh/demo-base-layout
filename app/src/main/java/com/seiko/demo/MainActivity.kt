package com.seiko.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.seiko.demo.base.BaseLayout
import com.seiko.demo.course.OnlineActivity

class MainActivity : AppCompatActivity() {
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

    init {
        btnGoToOnline.text = "在线直播"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        btnGoToOnline.autoMeasure()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        btnGoToOnline.layoutCenter()
    }
}