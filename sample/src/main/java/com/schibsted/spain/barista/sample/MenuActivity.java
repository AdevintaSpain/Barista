package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
    setSupportActionBar(toolbar);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.actions_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_action_1) {
      textView.setText("First menu option");
    } else if (item.getItemId() == R.id.menu_action_2) {
      textView.setText("Second menu option");
    }
    return super.onOptionsItemSelected(item);
  }
}
