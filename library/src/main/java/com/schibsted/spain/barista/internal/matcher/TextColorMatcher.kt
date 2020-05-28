package com.schibsted.spain.barista.internal.matcher

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import com.schibsted.spain.barista.internal.util.ColorResourceType
import com.schibsted.spain.barista.internal.util.colorResourceType
import org.hamcrest.Description

class TextColorMatcher(private val expectedColor: Int) : BoundedMatcher<View, TextView>(TextView::class.java) {

  private var colorName: String? = null

  override fun matchesSafely(textView: TextView): Boolean {

    colorName = when (expectedColor.colorResourceType) {
      ColorResourceType.COLOR_RES -> textView.context.resources.getResourceEntryName(expectedColor)
      ColorResourceType.COLOR_ATTR -> textView.context.resources.getResourceEntryName(expectedColor)
      ColorResourceType.COLOR_INT -> "$expectedColor"
    }

    return matchesColor(textView) || (expectedColor.colorResourceType == ColorResourceType.COLOR_RES && matchesColorList(textView))
  }

  private fun matchesColor(textView: TextView): Boolean {
    val currentColorInt = textView.currentTextColor

    val expectedColorInt = when (expectedColor.colorResourceType) {
      ColorResourceType.COLOR_RES -> ContextCompat.getColor(textView.context, expectedColor)
      ColorResourceType.COLOR_INT -> expectedColor
      ColorResourceType.COLOR_ATTR -> textView.context.getColorAttribute(expectedColor)
    }

    return currentColorInt == expectedColorInt
  }

  private fun Context.getColorAttribute(colorAttr: Int): Int {
    val outValue = TypedValue()
    theme.resolveAttribute(colorAttr, outValue, true)
    return outValue.data
  }

  private fun matchesColorList(textView: TextView): Boolean {
    val currentColorList = textView.textColors
    val expectedColorList = ContextCompat.getColorStateList(textView.context, expectedColor)!!

    val allStates = ALL_COLOR_STATE_LIST_STATES.flatMap { state -> listOf(state, -state) }.map { state -> intArrayOf(state) }

    return allStates.all { state ->
      val currentStateColor = currentColorList.getColorForState(state, currentColorList.defaultColor)
      val expectedStateColor = expectedColorList.getColorForState(state, expectedColorList.defaultColor)
      currentStateColor == expectedStateColor
    }
  }

  override fun describeTo(description: Description) {
    if (colorName != null) {
      val text = when (expectedColor.colorResourceType) {
        ColorResourceType.COLOR_RES -> "with text color resource: [$colorName]"
        ColorResourceType.COLOR_INT -> "with text color: [$expectedColor]"
        ColorResourceType.COLOR_ATTR -> "with text color attribute: [$colorName]"
      }

      description.appendText(text)
    } else {
      description.appendText("with text color: [$expectedColor]")
    }
  }

  companion object {
    private val ALL_COLOR_STATE_LIST_STATES = listOf(
      android.R.attr.state_focused,
      android.R.attr.state_window_focused,
      android.R.attr.state_enabled,
      android.R.attr.state_checkable,
      android.R.attr.state_checked,
      android.R.attr.state_selected,
      android.R.attr.state_pressed,
      android.R.attr.state_activated,
      android.R.attr.state_active,
      android.R.attr.state_single,
      android.R.attr.state_first,
      android.R.attr.state_middle,
      android.R.attr.state_last,
      android.R.attr.state_accelerated,
      android.R.attr.state_hovered,
      android.R.attr.state_drag_can_accept,
      android.R.attr.state_drag_hovered
    )
  }
}