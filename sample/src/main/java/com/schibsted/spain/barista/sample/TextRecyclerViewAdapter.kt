package com.schibsted.spain.barista.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextRecyclerViewAdapter internal constructor(items: Array<String>, clickedResult: TextView?) :
  RecyclerView.Adapter<TextRecyclerViewAdapter.ViewHolder>() {
  private val items: Array<String>
  private val clickedResult: TextView?
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val root = LayoutInflater.from(parent.context).inflate(R.layout.row_with_buttons, parent, false)
    val textView = root.findViewById<View>(R.id.textview) as TextView
    val yesButton = root.findViewById<View>(R.id.yes)
    val noButton = root.findViewById<View>(R.id.no)
    return ViewHolder(root, textView, yesButton, noButton)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.textView.text = items[position]
    holder.itemView.setOnClickListener { clickedResult!!.setText(ListsActivity.Companion.getRecyclerViewTextAt(holder.adapterPosition)) }
    holder.yesButton.setOnClickListener { clickedResult!!.text = "yes" }
    holder.noButton.setOnClickListener { clickedResult!!.text = "no" }
  }

  override fun getItemCount(): Int {
    return items.size
  }

  class ViewHolder(root: View?, var textView: TextView, var yesButton: View, var noButton: View) : RecyclerView.ViewHolder(
    root!!)

  init {
    this.items = items.clone()
    this.clickedResult = clickedResult
  }
}