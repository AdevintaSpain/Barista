package com.schibsted.spain.barista.sample

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView

class PreferencesActivity : Activity() {
  private var preferences: SharedPreferences? = null
  private var currentValueText: TextView? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_preferences)
    preferences = PreferenceManager.getDefaultSharedPreferences(this)
    currentValueText = findViewById<View>(R.id.preference_current_value) as TextView
    findViewById<View>(R.id.preference_increment_button).setOnClickListener {
      incrementValue()
      showCurrentValue()
    }
    showCurrentValue()
  }

  private fun showCurrentValue() {
    val currentValue = preferences!!.getInt(PREFERENCE_KEY, PREFERENCE_DEFAULT_VALUE)
    currentValueText!!.text = currentValue.toString()
  }

  private fun incrementValue() {
    val currentValue = preferences!!.getInt(PREFERENCE_KEY, PREFERENCE_DEFAULT_VALUE)
    val newValue = currentValue + 1
    preferences!!.edit()
      .putInt(PREFERENCE_KEY, newValue)
      .apply()
  }

  companion object {
    private const val PREFERENCE_KEY = "value"
    private const val PREFERENCE_DEFAULT_VALUE = 0
  }
}