package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SpinnerActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_spinner)
    val spinner = findViewById<View>(R.id.spinner) as Spinner
    spinner.adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, FRUITS)
  }

  companion object {
    private val FRUITS = arrayOf<String?>("Banana", "Apple", "Orange", "Raspberry")
  }
}