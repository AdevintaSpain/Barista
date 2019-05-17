package com.schibsted.spain.barista.sample;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputLayout;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class WrappedEditTextActivity extends AppCompatActivity {

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wrapped_edittext);

    TextView searchResult = findViewById(R.id.searchResult);

    android.widget.SearchView searchView = findViewById(R.id.searchview);
    SearchView supportSearchView = findViewById(R.id.supportSearchView);

    applyListener(searchResult, searchView);
    applySupportListener(searchResult, supportSearchView);

    applyAdapter(searchView);
    applySupportAdapter(supportSearchView);

    TextInputLayout textInputLayout = findViewById(R.id.textInput);
    applyTextInputListener(searchResult, textInputLayout);
  }

  private void applyListener(TextView searchResult, android.widget.SearchView searchView) {
    searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        searchResult.setText(query);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        searchResult.setText(newText);
        return true;
      }
    });
  }

  private void applySupportListener(TextView searchResult, SearchView searchView) {
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        searchResult.setText(query);
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        searchResult.setText(newText);
        return true;
      }
    });
  }

  private void applyTextInputListener(TextView searchResult, TextInputLayout textInputLayout) {
    EditText editText = textInputLayout.getEditText();
    if (editText != null) {
      editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          searchResult.setText(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
      });
    }
  }

  private void applyAdapter(android.widget.SearchView searchView) {
    MatrixCursor cursor = generateCursor();
    android.widget.CursorAdapter adapter = new CustomAdapter(this, cursor, true);
    searchView.setSuggestionsAdapter(adapter);
  }

  private void applySupportAdapter(SearchView searchView) {
    MatrixCursor cursor = generateCursor();
    CursorAdapter adapter = new CustomSupportAdapter(this, cursor, true);
    searchView.setSuggestionsAdapter(adapter);
  }

  @NonNull
  private MatrixCursor generateCursor() {
    MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "name" });
    cursor.addRow(new Object[] { 0, "a" });
    cursor.addRow(new Object[] { 1, "b" });
    return cursor;
  }

  private static class CustomAdapter extends android.widget.CursorAdapter {

    CustomAdapter(Context context, Cursor c, boolean autoRequery) {
      super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
      return new TextView(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
      if (view instanceof TextView) {
        ((TextView) view).setText(cursor.getString(1));
      }
    }
  }

  private static class CustomSupportAdapter extends CursorAdapter {

    CustomSupportAdapter(Context context, Cursor c, boolean autoRequery) {
      super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
      return new TextView(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
      if (view instanceof TextView) {
        ((TextView) view).setText(cursor.getString(1));
      }
    }
  }
}
