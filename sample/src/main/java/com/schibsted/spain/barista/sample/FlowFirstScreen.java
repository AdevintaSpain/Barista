package com.schibsted.spain.barista.sample;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;

public class FlowFirstScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flow_1);

    setOnClickListener(R.id.half_hidden);
    setOnClickListener(R.id.next);
    setOnClickListener(R.id.really_far_away_button);
    setOnClickListener(R.id.centered_button);

    hideUpperButtonAtHalf();
  }

  private void hideUpperButtonAtHalf() {
    final View halfHiddenButton = findViewById(R.id.half_hidden);
    halfHiddenButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        int height = halfHiddenButton.getHeight();
        int topPos = halfHiddenButton.getTop();
        int halfHiddenDistance = topPos + height / 2;

        ScrollView scroll = (ScrollView) findViewById(R.id.scrollView);
        scroll.scrollTo(0, halfHiddenDistance);
        halfHiddenButton.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }

  private void setOnClickListener(int view) {
    findViewById(view).setOnClickListener(new OpenSecondScreen());
    findViewById(view).setOnLongClickListener(new ChangeButtonText());
  }

  class OpenSecondScreen implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      startActivity(new Intent(FlowFirstScreen.this, FlowSecondScreen.class));
    }
  }

  static class ChangeButtonText implements View.OnLongClickListener {
    @Override
    public boolean onLongClick(View view) {
      ((Button) view).setText("I was long pressed");
      return true;
    }
  }
}
