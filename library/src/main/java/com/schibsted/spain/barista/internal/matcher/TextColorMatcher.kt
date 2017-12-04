package com.schibsted.spain.barista.internal.matcher

import android.support.annotation.ColorRes
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import org.hamcrest.Description

class TextColorMatcher(@ColorRes private val color: Int) : BoundedMatcher<View, TextView>(TextView::class.java) {

    private var colorName: String? = null

    override fun matchesSafely(textView: TextView): Boolean {
        val resources = textView.context.resources
        colorName = resources.getResourceEntryName(color)
        val colorMatches = textView.currentTextColor == ContextCompat.getColor(textView.context, color)
        return if (colorMatches) {
            true
        } else {
            val colorStateList = ContextCompat.getColorStateList(textView.context, color)
            val colorForState = colorStateList.getColorForState(intArrayOf(-android.R.attr.enabled), colorStateList.defaultColor)
            textView.currentTextColor == colorForState
        }
    }

    override fun describeTo(description: Description) {
        if (colorName == null) {
            description.appendText("with text color resource: [$color]")
        } else {
            description.appendText("with text color: [$colorName]")
        }
    }

}