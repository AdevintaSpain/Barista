package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class FileActivity extends Activity {

  public static final String EXTRA_PATH = "path";

  private TextView currentValueText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_file);

    currentValueText = ((TextView) findViewById(R.id.file_current_value));
    findViewById(R.id.file_increment_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        incrementValue();
        showCurrentValue();
      }
    });

    showCurrentValue();
  }

  private void showCurrentValue() {
    currentValueText.setText(String.valueOf(getOldValue()));
  }

  private void incrementValue() {
    try {
      int oldValue = getOldValue();
      int incrementedValue = oldValue + 1;

      getDirectory().mkdir();
      Writer out = new OutputStreamWriter(new FileOutputStream(getFile()));
      out.write("" + incrementedValue);
      out.close();
    } catch (Exception e) {
      Log.e("ERROR", "Error saving the File", e);
    }
  }

  private int getOldValue() {
    File file = getFile();
    int readValue = -1;
    Scanner scanner = null;
    try {
      scanner = new Scanner(new FileInputStream(file));
      while (scanner.hasNextLine()) {
        readValue = Integer.parseInt(scanner.nextLine());
      }
    } catch (FileNotFoundException notFound) {
      return 0;
    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
    return readValue;
  }

  @NonNull
  private File getFile() {
    String path = getIntent().getStringExtra(EXTRA_PATH);
    return new File(this.getFilesDir() + path, "sample.txt");
  }

  @NonNull
  private File getDirectory() {
    String path = getIntent().getStringExtra(EXTRA_PATH);
    return new File(this.getFilesDir() + path);
  }
}
