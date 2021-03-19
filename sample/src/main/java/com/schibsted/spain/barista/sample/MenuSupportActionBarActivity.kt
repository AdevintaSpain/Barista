package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuSupportActionBarActivity : AppCompatActivity() {
  private var textView: TextView? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)
    textView = findViewById<View>(R.id.menu_text) as TextView
    val toolbar = findViewById<View>(R.id.menu_toolbar) as Toolbar
    setSupportActionBar(toolbar)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    super.onCreateOptionsMenu(menu)
    menuInflater.inflate(R.menu.actions_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.menu_action_1) {
      textView!!.text = "First menu option"
      return true
    } else if (item.itemId == R.id.menu_action_2) {
      textView!!.text = "Second menu option"
      return true
    } else if (item.itemId == R.id.menu_action_3) {
      textView!!.text = "Third menu option"
      return true
    }
    return super.onOptionsItemSelected(item)
  }
}