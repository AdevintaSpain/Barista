package com.schibsted.spain.barista.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dialog);

    final TextView textView = (TextView) findViewById(R.id.dialog_selected_button_value);

    final AlertDialog.Builder dialog = new AlertDialog.Builder(this)
        .setTitle("Dialog")

        .setPositiveButton("positive", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            textView.setText("positive");
          }
        })
        .setNegativeButton("negative", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            textView.setText("negative");
          }
        })
        .setNeutralButton("neutral", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            textView.setText("neutral");
          }
        });

    Button button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.create().show();
      }
    });
  }
}
