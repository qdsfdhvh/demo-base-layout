package com.seiko.demo.chat

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import com.seiko.demo.R
import com.seiko.demo.base.BaseActivity
import com.seiko.demo.base.CustomLayout
import com.seiko.demo.utils.getAttrColor
import com.seiko.demo.utils.toTheme

class ChatActivity : BaseActivity() {

  private val edgeInsetDelegate by lazy(LazyThreadSafetyMode.NONE) {
    EdgeInsetDelegate(this)
  }

  @SuppressLint("WrongConstant")
  @Suppress("DEPRECATION")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val contentView = ChatLayout(this)
    setContentView(contentView)

    ViewCompat.getWindowInsetsController(window.decorView)?.let {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        it.show(WindowInsets.Type.systemBars())
      }
      it.isAppearanceLightStatusBars = true
      it.isAppearanceLightNavigationBars = true
    }

    edgeInsetDelegate.start()

    with(contentView.recyclerView) {
      layoutManager = LinearLayoutManager(this@ChatActivity, RecyclerView.VERTICAL, true)
      adapter = ChatMsgAdapter()
    }

    with(contentView.toolbar) {
      doOnApplyWindowInsets { insets, padding, _ ->
        updatePadding(top = padding.top + insets.systemWindowInsetTop)
      }
    }
    with(contentView.messageHolder) {
      doOnApplyWindowInsets { insets, padding, _ ->
        updatePadding(bottom = padding.bottom + insets.systemWindowInsetBottom)
      }
    }
  }
}

@SuppressLint("SetTextI18n")
class ChatLayout(context: Context) : CustomLayout(context) {

  private val toolbarLogo = ImageView(context).apply {
    setImageResource(R.drawable.ic_baseline_face_24)
  }

  private val toolbarTitle = TextView(context).apply {
    text = " IME Animations"
  }

  @JvmField
  val toolbar = MaterialToolbar(
    context.toTheme(R.style.Theme_App_Toolbar),
    null,
    R.attr.toolbarStyle
  ).autoAddView(MATCH_PARENT, WRAP_CONTENT) {
    elevation = 4.dp.toFloat()
    this.addView(toolbarLogo)
    this.addView(toolbarTitle)
  }

  @JvmField
  val recyclerView = RecyclerView(context).autoAddView(width = MATCH_PARENT)

  @JvmField
  val messageHolder = ChatMessageHolderView(context).autoAddView(MATCH_PARENT, 48.dp)

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    toolbar.autoMeasure()
    messageHolder.autoMeasure()

    val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
    val recyclerViewHeight = parentHeight - toolbar.measuredHeight - messageHolder.measuredHeight
    recyclerView.autoMeasure(heightMeasureSpec = recyclerViewHeight.toExactlyMeasureSpec())
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    layoutVertical(toolbar, recyclerView, messageHolder)
  }
}

class ChatMessageHolderView(context: Context) : CustomLayout(context) {

  private val inputEdit = AppCompatEditText(context).apply {
    hint = "Type a message..."
  }

  @JvmField
  val input = TextInputLayout(
    context.toTheme(R.style.Theme_App_InputLayout),
    null,
    R.attr.textInputStyle
  ).autoAddView(height = MATCH_PARENT) {
    this.addView(inputEdit)
  }

  @JvmField
  val btnSend = ImageButton(
    context.toTheme(R.style.Theme_App_SendButton),
    null,
    R.attr.imageButtonStyle
  ).autoAddView(48.dp) {
    setImageResource(R.drawable.ic_baseline_send_24)
  }

  init {
    setBackgroundColor(context.getAttrColor(R.attr.colorSurface))
    elevation = 4.dp.toFloat()
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    btnSend.autoMeasure()

    val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
    val inputWidth = parentWidth - btnSend.measuredWidth
    input.autoMeasure(widthMeasureSpec = inputWidth.toExactlyMeasureSpec())

    setMeasuredDimension(
      widthMeasureSpec,
      (input.measuredHeight + paddingTop + paddingBottom).toExactlyMeasureSpec()
    )
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    input.layout(0, 0)
    btnSend.layout(input.measuredWidth, 0)
  }
}