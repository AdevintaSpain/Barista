package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ViewPagerButtonFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val root = inflater.inflate(R.layout.activity_centered_button, container, false)
    root.findViewById<View>(R.id.button).setOnClickListener {
      val tv = root.findViewById<View>(R.id.textview) as TextView
      tv.setText(R.string.click)
    }
    root.findViewById<View>(R.id.really_far_away_button).setOnClickListener {
      val tv = root.findViewById<View>(R.id.textview) as TextView
      tv.setText(R.string.click_far_away)
    }
    return root
  }
}