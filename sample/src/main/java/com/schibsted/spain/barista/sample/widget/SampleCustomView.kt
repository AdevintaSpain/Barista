package com.schibsted.spain.barista.sample.widget;

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import com.schibsted.spain.barista.sample.R
import kotlinx.android.synthetic.main.sample_custom_view.view.customTextView

class SampleCustomView @JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = R.attr.sampleCustomViewStyle,
    defStyleRes: Int = R.style.SampleCustomStyle
) : LinearLayout(context, attributeSet, defStyleAttr) {

  private var customColor: Int = Color.GRAY

  init {
    context.withStyledAttributes(
        attributeSet,
        R.styleable.SampleCustomView,
        defStyleAttr,
        defStyleRes
    ) {
      customColor = getColor(R.styleable.SampleCustomView_customColor, customColor)
    }

    LayoutInflater.from(context).inflate(R.layout.sample_custom_view, this, true)
    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

    customTextView.setTextColor(customColor)
  }
}