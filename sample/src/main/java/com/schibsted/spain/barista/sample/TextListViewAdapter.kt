package com.schibsted.spain.barista.sample

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TextListViewAdapter internal constructor(private val activity: Activity, items: Array<String>, clickedResult: TextView?) :
  BaseAdapter() {
  private val items: Array<String>
  private val clickedResult: TextView?
  override fun getCount(): Int {
    return items.size
  }

  override fun getItem(position: Int): Any {
    return items[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    var rowView = convertView
    if (convertView == null) {
      val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
      rowView = inflater.inflate(R.layout.row_with_buttons, parent, false)
    }
    val textView = rowView?.findViewById<View>(R.id.textview) as TextView
    val yesButton = rowView?.findViewById<View>(R.id.yes)
    val noButton = rowView?.findViewById<View>(R.id.no)
    textView.text = items[position]
    rowView?.setOnClickListener { clickedResult!!.setText(ListsActivity.Companion.getComplexListViewTextAt(position)) }
    yesButton.setOnClickListener { clickedResult!!.text = "yes" }
    noButton.setOnClickListener { clickedResult!!.text = "no" }
    return rowView
  }

  init {
    this.items = items.clone()
    this.clickedResult = clickedResult
  }
}