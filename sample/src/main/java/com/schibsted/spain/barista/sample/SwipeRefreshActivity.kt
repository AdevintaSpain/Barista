package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class SwipeRefreshActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_swipe_refresh)
    val swipeRefreshLayout = findViewById<View>(R.id.swiperefresh) as SwipeRefreshLayout
    swipeRefreshLayout.setOnRefreshListener {
      val textView = findViewById<View>(R.id.textview) as TextView
      textView.visibility = View.VISIBLE
      swipeRefreshLayout.isRefreshing = false
    }
  }
}