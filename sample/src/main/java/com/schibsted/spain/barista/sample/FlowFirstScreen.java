package com.schibsted.spain.barista.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FlowFirstScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flow_1);

    setOnClickListener(R.id.next);
    setOnClickListener(R.id.really_far_away_button);
    setOnClickListener(R.id.centered_button);
  }

  private void setOnClickListener(int view) {
    findViewById(view).setOnClickListener(new OpenSecondScreen());
  }

  class OpenSecondScreen implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      startActivity(new Intent(FlowFirstScreen.this, FlowSecondScreen.class));
    }
  }
}
