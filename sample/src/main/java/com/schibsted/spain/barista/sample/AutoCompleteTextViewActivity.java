package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

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
