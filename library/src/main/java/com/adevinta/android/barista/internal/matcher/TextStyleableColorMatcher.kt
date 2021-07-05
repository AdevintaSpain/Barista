package com.adevinta.android.barista.internal.matcher
import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.test.espresso.matcher.BoundedMatcher
import com.adevinta.android.barista.internal.util.ColorResourceType
import com.adevinta.android.barista.internal.util.colorResourceType
import org.hamcrest.Description

class TextStyleableColorMatcher(
    @StyleableRes private val styleableRes: IntArray,
    @StyleRes private val styleRes: Int,
    @StyleableRes private val attrColor: Int
) : BoundedMatcher<View, TextView>(TextView::class.java) {

    private var styleResName: String? = null

    override fun matchesSafely(textView: TextView): Boolean {
        styleResName = textView.context.resources.getResourceEntryName(styleRes)

        return matchesColor(textView) || matchesColorList(textView)
    }

    private fun matchesColor(textView: TextView): Boolean {
        val currentColorInt = textView.currentTextColor
        val expectedColorInt = getStyleableColor(textView)
        return currentColorInt == expectedColorInt
    }

    private fun matchesColorList(textView: TextView): Boolean {
        val currentColorList = textView.textColors
        val expectedColorList = getStyleableColorList(textView)
        return currentColorList == expectedColorList
    }

    private fun getStyleableColor(textView: TextView): Int {
        val typedArray = textView.context.obtainStyledAttributes(
                null,
                styleableRes,
                0,
                styleRes
        )

        val expectedColorInt = typedArray.getColor(attrColor, -1)

        typedArray.recycle()
        return expectedColorInt
    }

    private fun getStyleableColorList(textView: TextView): ColorStateList? {
        val typedArray = textView.context.obtainStyledAttributes(
                null,
                styleableRes,
                0,
                styleRes
        )

        val expectedColorInt = typedArray.getColorStateList(attrColor)

        typedArray.recycle()
        return expectedColorInt
    }

    override fun describeTo(description: Description) {
        description.appendText("with style matching [$styleResName]")
    }
}