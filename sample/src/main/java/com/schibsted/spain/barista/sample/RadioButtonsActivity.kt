package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

class RadioButtonsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_radiobuttons)
    val radioGroup = findViewById<View>(R.id.radiogroup) as RadioGroup
    val reallyFarAwayRadioGroup = findViewById<View>(R.id.radiogroup_really_far_away) as RadioGroup
    radioGroup.setOnCheckedChangeListener(PutClickedIdOnTextView())
    reallyFarAwayRadioGroup.setOnCheckedChangeListener(PutClickedIdOnTextView())
  }

  private inner class PutClickedIdOnTextView : RadioGroup.OnCheckedChangeListener {
    override fun onCheckedChanged(group: RadioGroup, @IdRes checkedId: Int) {
      val textView = findViewById<View>(R.id.selected_item) as TextView
      textView.text = checkedId.toString()
    }
  }
}