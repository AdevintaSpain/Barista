package com.schibsted.spain.barista.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import com.schibsted.spain.barista.sample.widget.SampleImageView;

public class SomeViewsWithDifferentVisibilitiesActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.edittext_with_focus).requestFocus();

    ImageView codeVectorImageView = findViewById(R.id.code_vector_image_view);
    codeVectorImageView.setImageResource(R.drawable.barista_logo_vector);

    SampleImageView customCodeVectorImageView = findViewById(R.id.custom_code_vector_image_view);
    Drawable vector = AppCompatResources.getDrawable(this, R.drawable.barista_logo_vector);
    customCodeVectorImageView.setImageDrawable(vector);
  }
}
