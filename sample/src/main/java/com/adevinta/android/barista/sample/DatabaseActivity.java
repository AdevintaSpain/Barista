package com.adevinta.android.barista.sample;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DatabaseActivity extends Activity {

  private TextView currentValueText;
  private DatabaseOpenHelper databaseOpenHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_database);

    databaseOpenHelper = new DatabaseOpenHelper(this);
    currentValueText = ((TextView) findViewById(R.id.database_current_value));
    findViewById(R.id.database_increment_button).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        incrementValue();
        showCurrentValue();
      }
    });

    showCurrentValue();
  }

  private void showCurrentValue() {
    SQLiteDatabase readableDatabase = databaseOpenHelper.getReadableDatabase();
    Cursor cursor = readableDatabase.query("User", new String[] { "name" }, null, null, null, null, null);

    int currentValue = cursor.getCount();
    currentValueText.setText(String.valueOf(currentValue));

    cursor.close();
    readableDatabase.close();
  }

  private void incrementValue() {
    SQLiteDatabase writableDatabase = databaseOpenHelper.getWritableDatabase();
    ContentValues contentValues = new ContentValues(1);
    contentValues.put("name", "Mr T");
    writableDatabase.insert("User", null, contentValues);
    writableDatabase.close();
  }

  private static class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    DatabaseOpenHelper(Context context) {
      super(context, "mydatabase.db", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE User (name TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
  }
}
