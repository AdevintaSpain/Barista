package com.adevinta.android.barista.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class WrappedViewActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wrapped_view);

    findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        findViewById(R.id.text).setVisibility(View.VISIBLE);
      }
    });
  }
}
