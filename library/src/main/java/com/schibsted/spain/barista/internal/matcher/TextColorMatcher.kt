package com.schibsted.spain.barista.internal.matcher

import android.content.Context
import androidx.annotation.ColorRes
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.TextView
import org.hamcrest.Description

class TextColorMatcher(@ColorRes private val colorRes: Int) : BoundedMatcher<View, TextView>(TextView::class.java) {

  private var colorName: String? = null

  override fun matchesSafely(textView: TextView): Boolean {
    val context = textView.context
    val resources = context.resources
    colorName = resources.getResourceEntryName(colorRes)

    val matchesColor = matchesColor(context, textView)
    val matchesColorStateList = matchesColorList(context, textView)

    return matchesColor || matchesColorStateList
  }

  private fun matchesColor(context: Context, textView: TextView): Boolean {
    val currentColorInt = textView.currentTextColor
    val expectedColorInt = ContextCompat.getColor(context, colorRes)
    return currentColorInt == expectedColorInt
  }

  private fun matchesColorList(context: Context, textView: TextView): Boolean {
    val currentColorList = textView.textColors
    val expectedColorList = ContextCompat.getColorStateList(context, colorRes)
    return currentColorList == expectedColorList
  }

  override fun describeTo(description: Description) {
    if (colorName == null) {
      description.appendText("with text color resource: [$colorRes]")
    } else {
      description.appendText("with text color: [$colorName]")
    }
  }
}