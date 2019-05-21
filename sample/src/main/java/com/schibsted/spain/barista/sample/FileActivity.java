package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class FileActivity extends Activity {

  public static final String EXTRA_PATH = "path";

  private static final String SAMPLE_FILE_NAME = "sample.txt";

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
    } catch (IOException ignore) {
      Log.e("ERROR", "Error saving the File", ignore);
    }
  }

  private int getOldValue() {
    File file = getFile();

    try {
      int readValue = 0;
      Scanner scanner = new Scanner(new FileInputStream(file));
      while (scanner.hasNextLine()) {
        readValue = Integer.parseInt(scanner.nextLine());
      }
      scanner.close();
      return readValue;
    } catch (FileNotFoundException notFound) {
      return 0;
    }
  }

  @NonNull
  private File getFile() {
    return new File(getDirectory(), SAMPLE_FILE_NAME);
  }

  @NonNull
  private File getDirectory() {
    String path = getIntent().getStringExtra(EXTRA_PATH);
    return new File(this.getFilesDir() + path);
  }
}
