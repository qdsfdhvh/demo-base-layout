package com.seiko.demo.utils

import android.content.Context
import androidx.annotation.StyleRes
import androidx.appcompat.view.ContextThemeWrapper

fun Context.toTheme(@StyleRes styleId: Int): Context {
  return ContextThemeWrapper(this, styleId)
}