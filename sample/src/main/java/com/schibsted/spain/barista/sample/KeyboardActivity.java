package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class KeyboardActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_keyboard);

    EditText editText = (EditText) findViewById(R.id.edittext);
    editText.setOnEditorActionListener(new PutActionsOnTextView());
  }

  private class PutActionsOnTextView implements EditText.OnEditorActionListener {
    @Override
    public boolean onEditorAction(TextView editText, int actionId, KeyEvent keyEvent) {
      TextView textView = (TextView) findViewById(R.id.actions);
      textView.setText("Ime action button pressed!");
      return true;
    }
  }
}
