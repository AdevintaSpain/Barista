package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
