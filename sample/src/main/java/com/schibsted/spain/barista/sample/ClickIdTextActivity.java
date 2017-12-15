package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ClickIdTextActivity extends AppCompatActivity {

  private TextView actionsTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_click_id_text);

    actionsTextView = (TextView) findViewById(R.id.actions);

    setOnClickListener("button1");
    setOnClickListener("button2");

    setOnLongClickListener("button1");
    setOnLongClickListener("button2");
  }

  private void setOnClickListener(Object tag) {
    getWindow().getDecorView().findViewWithTag(tag).setOnClickListener(new OnButtonClickListener());
  }

  private void setOnLongClickListener(Object tag) {
    getWindow().getDecorView().findViewWithTag(tag).setOnLongClickListener(new OnButtonLongClickListener());
  }

  class OnButtonClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      TextView textView = (TextView) view;
      actionsTextView.setText(textView.getText() + " clicked!");
    }
  }

  class OnButtonLongClickListener implements View.OnLongClickListener {
    @Override
    public boolean onLongClick(View view) {
      TextView textView = (TextView) view;
      actionsTextView.setText(textView.getText() + " long clicked!");
      return true;
    }
  }
}
