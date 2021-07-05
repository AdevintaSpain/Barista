package com.adevinta.android.barista.sample;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

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
