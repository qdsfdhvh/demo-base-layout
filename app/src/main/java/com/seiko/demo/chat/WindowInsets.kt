package com.seiko.demo.chat

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

typealias InitialPadding = Rect
typealias InitialMargin = Rect

@Suppress("DEPRECATION")
fun View.applyBottomWindowInsetForScrollingView(scrollingView: ViewGroup) {
  scrollingView.clipToPadding = false
  val scrollingViewPadding = scrollingView.recordInitialPaddingForView()
  doOnApplyWindowInsets { insets, _, _ ->
    scrollingView.updatePadding(bottom = scrollingViewPadding.bottom + insets.systemWindowInsetBottom)
  }
}

fun View.doOnApplyWindowInsets(
  block: (insets: WindowInsetsCompat, padding: InitialPadding, margin: InitialMargin) -> Unit
) {
  val initialPadding = recordInitialPaddingForView()
  val initialMargin = recordInitialMarginForView()
  ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
    block(insets, initialPadding, initialMargin)
    insets
  }
  requestApplyInsetsWhenAttached()
}

fun View.recordInitialPaddingForView() =
  InitialPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)

fun View.recordInitialMarginForView() =
  (layoutParams as? ViewGroup.MarginLayoutParams)?.let {
    InitialMargin(it.leftMargin, it.topMargin, it.rightMargin, it.bottomMargin)
  } ?: InitialMargin(0, 0, 0, 0)

fun View.requestApplyInsetsWhenAttached() {
  if (isAttachedToWindow) {
    requestApplyInsets()
  } else {
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
      override fun onViewAttachedToWindow(v: View) {
        v.removeOnAttachStateChangeListener(this)
        v.requestApplyInsets()
      }

      override fun onViewDetachedFromWindow(v: View) = Unit
    })
  }
}