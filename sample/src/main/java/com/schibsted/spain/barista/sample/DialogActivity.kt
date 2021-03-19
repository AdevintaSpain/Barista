package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class DialogActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dialog)
    val textView = findViewById<View>(R.id.dialog_selected_button_value) as TextView
    val dialog = AlertDialog.Builder(this)
      .setTitle("Dialog")
      .setPositiveButton("positive") { dialog, which -> textView.text = "positive" }
      .setNegativeButton("negative") { dialog, which -> textView.text = "negative" }
      .setNeutralButton("neutral") { dialog, which -> textView.text = "neutral" }
    val button = findViewById<View>(R.id.button) as Button
    button.setOnClickListener { dialog.create().show() }
  }
}