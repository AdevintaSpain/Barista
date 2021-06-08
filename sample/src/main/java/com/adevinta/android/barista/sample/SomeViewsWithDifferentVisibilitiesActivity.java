package com.adevinta.android.barista.sample;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SomeViewsWithDifferentVisibilitiesActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.edittext_with_focus).requestFocus();

    ImageView codeVectorImageView = findViewById(R.id.code_vector_image_view);
    codeVectorImageView.setImageResource(R.drawable.barista_logo_vector);
  }
}
