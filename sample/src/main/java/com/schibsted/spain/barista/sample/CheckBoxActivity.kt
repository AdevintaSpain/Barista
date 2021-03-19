package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheckBoxActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_checkbox)
    val selectedItem = findViewById<View>(R.id.selected_item) as TextView
    setListener(selectedItem, R.id.first_item)
    setListener(selectedItem, R.id.second_item)
  }

  private fun setListener(selectedItem: TextView, checkBoxId: Int) {
    (findViewById<View>(checkBoxId) as CheckBox).setOnCheckedChangeListener(CheckListener(selectedItem))
  }

  internal class CheckListener(private val selectedItem: TextView) : CompoundButton.OnCheckedChangeListener {
    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
      selectedItem.text = "" + compoundButton.id
    }
  }
}