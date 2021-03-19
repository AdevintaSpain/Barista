package com.schibsted.spain.barista.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class NestedScrollViewActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nested_scroll)
    setOnClickListener(R.id.really_far_away_button)
  }

  private fun setOnClickListener(view: Int) {
    findViewById<View>(view).setOnClickListener(OpenSecondScreen())
  }

  internal inner class OpenSecondScreen : View.OnClickListener {
    override fun onClick(view: View) {
      startActivity(Intent(this@NestedScrollViewActivity, FlowSecondScreen::class.java))
    }
  }
}