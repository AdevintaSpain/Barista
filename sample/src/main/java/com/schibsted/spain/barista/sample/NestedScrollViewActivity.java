package com.schibsted.spain.barista.sample;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class NestedScrollViewActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nested_scroll);

    setOnClickListener(R.id.really_far_away_button);
  }

  private void setOnClickListener(int view) {
    findViewById(view).setOnClickListener(new NestedScrollViewActivity.OpenSecondScreen());
  }

  class OpenSecondScreen implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      startActivity(new Intent(NestedScrollViewActivity.this, FlowSecondScreen.class));
    }
  }
}
