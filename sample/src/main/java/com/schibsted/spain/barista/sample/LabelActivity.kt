package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LabelActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_centered_label)
    val selectedItem = findViewById<View>(R.id.selected_item) as TextView
    val value = intent.getStringExtra(EXTRA_TEXT)
    selectedItem.text = value
  }

  companion object {
    const val EXTRA_TEXT = "text"
  }
}