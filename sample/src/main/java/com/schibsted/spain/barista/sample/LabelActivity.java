package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class LabelActivity extends AppCompatActivity {

  public static final String EXTRA_TEXT = "text";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_centered_label);

    TextView selectedItem = (TextView) findViewById(R.id.selected_item);
    String value = getIntent().getStringExtra(EXTRA_TEXT);
    selectedItem.setText(value);
  }
}
