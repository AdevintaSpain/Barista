package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

  private TextView textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);

    textView = (TextView) findViewById(R.id.menu_text);

    Toolbar toolbar = (Toolbar) findViewById(R.id.menu_toolbar);
    toolbar.inflateMenu(R.menu.actions_menu);
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.menu_action_1) {
          textView.setText("First menu option");
          return true;
        } else if (item.getItemId() == R.id.menu_action_2) {
          textView.setText("Second menu option");
          return true;
        } else if (item.getItemId() == R.id.menu_action_3) {
          textView.setText("Third menu option");
          return true;
        }
        return false;
      }
    });
  }
}
