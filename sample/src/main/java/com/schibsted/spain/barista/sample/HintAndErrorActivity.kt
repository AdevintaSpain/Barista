package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class HintAndErrorActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hintanderrortext)
    findViewById<View>(R.id.showErrors).setOnClickListener { v: View? -> showError() }
  }

  private fun showError() {
    val inputLayout = findViewById<TextInputLayout>(R.id.hintanderror_inputlayout)
    inputLayout.error = getString(R.string.hintanderror_inputlayout_error)
    val inputEditText = findViewById<TextInputEditText>(R.id.hintanderror_inputedittext)
    inputEditText.error = getString(R.string.hintanderror_inputedittext_error)
    val editText = findViewById<EditText>(R.id.hintanderror_edittext)
    editText.error = getString(R.string.hintanderror_edittext_error)
  }
}