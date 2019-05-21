package com.schibsted.spain.barista.sample;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

@TargetApi(Build.VERSION_CODES.M)
public class RuntimePermissionActivity extends AppCompatActivity {

  public static final String SOME_PERMISSION = Manifest.permission.READ_CONTACTS;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_runtime_permission);

    findViewById(R.id.request_permission_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        requestPermission();
      }
    });

    findViewById(R.id.use_permission_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        usePermission();
      }
    });
  }

  private void requestPermission() {
    ActivityCompat.requestPermissions(this, new String[] { SOME_PERMISSION }, 1);
  }

  private void usePermission() {
    if (ContextCompat.checkSelfPermission(this, SOME_PERMISSION) != PackageManager.PERMISSION_GRANTED) {
      throw new IllegalStateException("Tried to use permission without having it");
    }
  }
}
