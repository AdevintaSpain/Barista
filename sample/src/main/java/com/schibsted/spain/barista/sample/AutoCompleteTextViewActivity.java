package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

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
    autoComplete.setOnEditorActionListener(new PutActionsOnTextView());

    veryFarAwayAutoComplete.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, FRUITS));
    veryFarAwayAutoComplete.setThreshold(1);
  }

  private class PutActionsOnTextView implements EditText.OnEditorActionListener {
    @Override
    public boolean onEditorAction(TextView editText, int actionId, KeyEvent keyEvent) {
      TextView textView = (TextView) findViewById(R.id.actions);
      textView.setText("Submitted!");
      return true;
    }
  }
}
