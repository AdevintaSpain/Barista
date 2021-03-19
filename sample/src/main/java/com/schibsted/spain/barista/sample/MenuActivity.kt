package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MenuActivity : AppCompatActivity() {
  private var textView: TextView? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)
    textView = findViewById<View>(R.id.menu_text) as TextView
    val toolbar = findViewById<View>(R.id.menu_toolbar) as Toolbar
    toolbar.inflateMenu(R.menu.actions_menu)
    toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->
      if (item.itemId == R.id.menu_action_1) {
        textView!!.text = "First menu option"
        return@OnMenuItemClickListener true
      } else if (item.itemId == R.id.menu_action_2) {
        textView!!.text = "Second menu option"
        return@OnMenuItemClickListener true
      } else if (item.itemId == R.id.menu_action_3) {
        textView!!.text = "Third menu option"
        return@OnMenuItemClickListener true
      }
      false
    })
  }
}