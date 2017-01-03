package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutoCompleteTextViewActivity extends AppCompatActivity {

    private static final String[] FRUITS = {"Banana", "Apple", "Orange", "Raspberry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocompletetextview);

        AutoCompleteTextView autocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete);
        AutoCompleteTextView veryFarAwayAutocomplete = (AutoCompleteTextView) findViewById(R.id.autocomplete_very_far_away);

        autocomplete.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS));
        autocomplete.setThreshold(1);

        veryFarAwayAutocomplete.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS));
        veryFarAwayAutocomplete.setThreshold(1);
    }
}
