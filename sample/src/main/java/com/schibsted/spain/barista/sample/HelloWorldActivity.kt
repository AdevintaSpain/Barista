package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class HelloWorldActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hello_world)
    val btn_closed = findViewById<View>(R.id.btn_closed) as Button
    btn_closed.setOnClickListener { finish() }
    val textNight = findViewById<TextView>(R.id.text_night)
    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
      textNight.visibility = View.VISIBLE
    } else {
      textNight.visibility = View.GONE
    }
  }
}