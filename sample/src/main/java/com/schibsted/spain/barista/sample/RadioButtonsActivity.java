package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButtonsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_radiobuttons);

    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int itemId) {
        TextView textView = (TextView) findViewById(R.id.selected_item);
        textView.setText("" + itemId);
      }
    });
  }
}
