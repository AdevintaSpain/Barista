package com.schibsted.spain.barista.sample

import android.app.Activity
import android.os.Bundle
import android.view.View

class WrappedViewActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wrapped_view)
    findViewById<View>(R.id.button).setOnClickListener { findViewById<View>(R.id.text).visibility = View.VISIBLE }
  }
}