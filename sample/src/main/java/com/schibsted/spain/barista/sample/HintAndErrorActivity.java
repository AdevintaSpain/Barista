package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;

public class HintAndErrorActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hintanderrortext);

    showError();
  }

  private void showError() {
    TextInputLayout inputLayout = findViewById(R.id.hintanderror_inputlayout);
    inputLayout.setError(getString(R.string.hintanderror_inputlayout_error));

    TextInputEditText inputEditText = findViewById(R.id.hintanderror_inputedittext);
    inputEditText.setError(getString(R.string.hintanderror_inputedittext_error));

    EditText editText = findViewById(R.id.hintanderror_edittext);
    editText.setError(getString(R.string.hintanderror_edittext_error));
  }
}
