package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class KeyboardActivity extends AppCompatActivity {

  private TextView actionsTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_keyboard);

    actionsTextView = (TextView) findViewById(R.id.actions);
    initEditText();
    initEditTextNotFocused();
    initEditTextVeryFarAway();
  }

  private void initEditText() {
    EditText editText = (EditText) findViewById(R.id.edittext);
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        actionsTextView.setText("Edit text ime action button pressed!");
        return true;
      }
    });
  }

  private void initEditTextNotFocused() {
    EditText editText = (EditText) findViewById(R.id.edittext_not_focused);
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        actionsTextView.setText("Edit text not focused ime action button pressed!");
        return true;
      }
    });
  }

  private void initEditTextVeryFarAway() {
    EditText editText = (EditText) findViewById(R.id.edittext_very_far_away);
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        actionsTextView.setText("Edit text very far away ime action button pressed!");
        return true;
      }
    });
  }
}
