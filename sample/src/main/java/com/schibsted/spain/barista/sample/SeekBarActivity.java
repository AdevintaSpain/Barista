package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seekbar);
    SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar);
    seekBar.setOnSeekBarChangeListener(new PutProgressOnTextView());
  }

  private class PutProgressOnTextView implements SeekBar.OnSeekBarChangeListener {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
      TextView textView = (TextView) findViewById(R.id.progress);
      textView.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
  }
}
