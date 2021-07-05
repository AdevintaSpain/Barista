package com.adevinta.android.barista.sample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class SpinnerActivity extends AppCompatActivity {

  private static final String[] FRUITS = { "Banana", "Apple", "Orange", "Raspberry" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spinner);

    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    spinner.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS));
  }
}
