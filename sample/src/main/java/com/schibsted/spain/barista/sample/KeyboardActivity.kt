package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class KeyboardActivity : AppCompatActivity() {
  private var actionsTextView: TextView? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_keyboard)
    actionsTextView = findViewById<View>(R.id.actions) as TextView
    initEditText()
    initEditTextNotFocused()
    initEditTextVeryFarAway()
  }

  private fun initEditText() {
    val editText = findViewById<View>(R.id.edittext) as EditText
    editText.setOnEditorActionListener { textView, i, keyEvent ->
      actionsTextView!!.text = "Edit text ime action button pressed!"
      true
    }
  }

  private fun initEditTextNotFocused() {
    val editText = findViewById<View>(R.id.edittext_not_focused) as EditText
    editText.setOnEditorActionListener { textView, i, keyEvent ->
      actionsTextView!!.text = "Edit text not focused ime action button pressed!"
      true
    }
  }

  private fun initEditTextVeryFarAway() {
    val editText = findViewById<View>(R.id.edittext_very_far_away) as EditText
    editText.setOnEditorActionListener { textView, i, keyEvent ->
      actionsTextView!!.text = "Edit text very far away ime action button pressed!"
      true
    }
  }
}