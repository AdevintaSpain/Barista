package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FlakyActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flaky);
  }
}
