package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity

class AutoCompleteTextViewActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_autocompletetextview)
    val autoComplete = findViewById<View>(R.id.autocomplete) as AutoCompleteTextView
    val veryFarAwayAutoComplete = findViewById<View>(R.id.autocomplete_very_far_away) as AutoCompleteTextView
    autoComplete.setAdapter<ArrayAdapter<*>>(ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, FRUITS))
    autoComplete.threshold = 1
    veryFarAwayAutoComplete.setAdapter<ArrayAdapter<*>>(ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, FRUITS))
    veryFarAwayAutoComplete.threshold = 1
  }

  companion object {
    private val FRUITS = arrayOf<String?>("Banana", "Apple", "Orange", "Raspberry")
  }
}