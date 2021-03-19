package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ViewPagerFirstFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.activity_centered_label, container, false)
    val label = view.findViewById<View>(R.id.selected_item) as TextView
    label.text = "" + 1
    return view
  }
}