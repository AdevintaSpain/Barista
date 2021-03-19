package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SomeViewsWithDifferentVisibilitiesActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    findViewById<View>(R.id.edittext_with_focus).requestFocus()
    val codeVectorImageView = findViewById<ImageView>(R.id.code_vector_image_view)
    codeVectorImageView.setImageResource(R.drawable.barista_logo_vector)
  }
}