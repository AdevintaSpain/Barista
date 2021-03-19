package com.schibsted.spain.barista.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class FlowFirstScreen : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_flow_1)
    setOnClickListener(R.id.half_hidden)
    setOnClickListener(R.id.next)
    setOnClickListener(R.id.really_far_away_button)
    setOnClickListener(R.id.centered_button)
    hideUpperButtonAtHalf()
  }

  private fun hideUpperButtonAtHalf() {
    val halfHiddenButton = findViewById<View>(R.id.half_hidden)
    halfHiddenButton.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        val height = halfHiddenButton.height
        val topPos = halfHiddenButton.top
        val halfHiddenDistance = topPos + height / 2
        val scroll = findViewById<View>(R.id.scrollView) as ScrollView
        scroll.scrollTo(0, halfHiddenDistance)
        halfHiddenButton.viewTreeObserver.removeGlobalOnLayoutListener(this)
      }
    })
  }

  private fun setOnClickListener(view: Int) {
    findViewById<View>(view).setOnClickListener(OpenSecondScreen())
    findViewById<View>(view).setOnLongClickListener(ChangeButtonText())
  }

  internal inner class OpenSecondScreen : View.OnClickListener {
    override fun onClick(view: View) {
      startActivity(Intent(this@FlowFirstScreen, FlowSecondScreen::class.java))
    }
  }

  internal class ChangeButtonText : OnLongClickListener {
    override fun onLongClick(view: View): Boolean {
      (view as Button).text = "I was long pressed"
      return true
    }
  }
}