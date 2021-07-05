package com.adevinta.android.barista.sample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

  private static final String[] FRUITS = { "Banana", "Apple", "Orange", "Raspberry" };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_autocompletetextview);

    AutoCompleteTextView autoComplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
    AutoCompleteTextView veryFarAwayAutoComplete = (AutoCompleteTextView) findViewById(R.id.autocomplete_very_far_away);

    autoComplete.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS));
    autoComplete.setThreshold(1);

    veryFarAwayAutoComplete.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS));
    veryFarAwayAutoComplete.setThreshold(1);
  }
}
