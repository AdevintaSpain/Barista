package com.schibsted.spain.barista.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class HintAndErrorActivity extends AppCompatActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_hintanderrortext);

    findViewById(R.id.hintanderror_showLayoutErrorButton).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showLayoutError();
      }
    });

    findViewById(R.id.hintanderror_showInputEditTextError).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showTextInputLayoutError();
      }
    });

    findViewById(R.id.hintanderror_showEditTextError).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showEditTextError();
      }
    });
  }

  private void showLayoutError() {
    TextInputLayout layout = (TextInputLayout) findViewById(R.id.hintanderror_inputlayout1);
    layout.setError(getResources().getString(R.string.hintanderror_layout_error));
  }

  private void showTextInputLayoutError() {
    TextInputEditText editText = (TextInputEditText) findViewById(R.id.hintanderror_inputEditText1);
    editText.setError(getResources().getString(R.string.hintanderror_text_input_layout_error));
  }

  private void showEditTextError() {
    EditText editText = (EditText) findViewById(R.id.hintanderror_edittext);
    editText.setError(getResources().getString(R.string.hintanderror_edittext_error));
  }
}
