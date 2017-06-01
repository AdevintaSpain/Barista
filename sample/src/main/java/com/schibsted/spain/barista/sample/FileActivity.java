package com.schibsted.spain.barista.sample;

import android.app.Activity;
import android.os.Bundle;
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

      Writer out = new OutputStreamWriter(new FileOutputStream(getFile()));
      out.write("" + incrementedValue);
      out.close();
    } catch (Exception ignore) {

    }
  }

  private int getOldValue() {
    File file = getFile();
    int readValue = -1;
    try {
      Scanner scanner = new Scanner(new FileInputStream(file));
      while (scanner.hasNextLine()) {
        readValue = Integer.parseInt(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException notFound) {
      return 0;
    }
    return readValue;
  }

  private File getFile() {
    return new File(this.getFilesDir() + "/", "sample.txt");
  }
}
