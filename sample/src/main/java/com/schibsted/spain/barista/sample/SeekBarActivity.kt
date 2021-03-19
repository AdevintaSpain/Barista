package com.schibsted.spain.barista.sample

import android.os.Bundle
import android.widget.RatingBar
import android.widget.RatingBar.OnRatingBarChangeListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SeekBarActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_seekbar)
    val seekBar = findViewById<SeekBar>(R.id.seek_bar)
    seekBar.setOnSeekBarChangeListener(PutSeekBarProgressOnTextView())
    val ratingBar = findViewById<RatingBar>(R.id.rating_bar)
    ratingBar.onRatingBarChangeListener = PutRatingBarProgressOnTextView()
  }

  private inner class PutSeekBarProgressOnTextView : OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
      val textView = findViewById<TextView>(R.id.seekbar_progress)
      textView.text = progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {}
    override fun onStopTrackingTouch(seekBar: SeekBar) {}
  }

  private inner class PutRatingBarProgressOnTextView : OnRatingBarChangeListener {
    override fun onRatingChanged(ratingBar: RatingBar, rating: Float, fromUser: Boolean) {
      val textView = findViewById<TextView>(R.id.ratingbar_progress)
      textView.text = rating.toString()
    }
  }
}