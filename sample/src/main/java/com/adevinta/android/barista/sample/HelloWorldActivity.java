package com.adevinta.android.barista.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class HelloWorldActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hello_world);

    Button btn_closed = (Button) findViewById(R.id.btn_closed);

    btn_closed.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });

    TextView textNight = findViewById(R.id.text_night);

    if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
      textNight.setVisibility(View.VISIBLE);
    } else {
      textNight.setVisibility(View.GONE);
    }
  }
}
