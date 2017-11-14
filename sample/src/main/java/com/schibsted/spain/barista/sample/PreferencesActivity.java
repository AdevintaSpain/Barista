package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class PreferencesActivity extends Activity {

  private static final String PREFERENCE_KEY = "value";
  private static final int PREFERENCE_DEFAULT_VALUE = 0;

  private SharedPreferences preferences;
  private TextView currentValueText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preferences);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    currentValueText = ((TextView) findViewById(R.id.preference_current_value));
    findViewById(R.id.preference_increment_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        incrementValue();
        showCurrentValue();
      }
    });

    showCurrentValue();
  }

  private void showCurrentValue() {
    int currentValue = preferences.getInt(PREFERENCE_KEY, PREFERENCE_DEFAULT_VALUE);
    currentValueText.setText(String.valueOf(currentValue));
  }

  private void incrementValue() {
    int currentValue = preferences.getInt(PREFERENCE_KEY, PREFERENCE_DEFAULT_VALUE);
    int newValue = currentValue + 1;
    preferences.edit()
        .putInt(PREFERENCE_KEY, newValue)
        .apply();
  }
}
