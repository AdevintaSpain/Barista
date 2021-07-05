package com.adevinta.android.barista.sample;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SeekBarActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seekbar);

    SeekBar seekBar = findViewById(R.id.seek_bar);
    seekBar.setOnSeekBarChangeListener(new PutSeekBarProgressOnTextView());
    RatingBar ratingBar = findViewById(R.id.rating_bar);
    ratingBar.setOnRatingBarChangeListener(new PutRatingBarProgressOnTextView());
  }

  private class PutSeekBarProgressOnTextView implements SeekBar.OnSeekBarChangeListener {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
      TextView textView = findViewById(R.id.seekbar_progress);
      textView.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
  }

  private class PutRatingBarProgressOnTextView implements RatingBar.OnRatingBarChangeListener {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
      TextView textView = findViewById(R.id.ratingbar_progress);
      textView.setText(String.valueOf(rating));
    }
  }
}
