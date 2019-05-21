package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RadioButtonsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_radiobuttons);

    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
    RadioGroup reallyFarAwayRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_really_far_away);

    radioGroup.setOnCheckedChangeListener(new PutClickedIdOnTextView());
    reallyFarAwayRadioGroup.setOnCheckedChangeListener(new PutClickedIdOnTextView());
  }

  private class PutClickedIdOnTextView implements RadioGroup.OnCheckedChangeListener {

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
      TextView textView = (TextView) findViewById(R.id.selected_item);
      textView.setText(String.valueOf(checkedId));
    }
  }
}
