package com.schibsted.spain.barista.cleardata;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.VisibleForTesting;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

  private static final String[] UNWANTED_FILENAME_SUFFIXES = new String[] {
      "-journal",
      "-shm",
      "-uid",
      "-wal"
  };

  static void clearAllDatabases(Context appContext) {
    for (File file : getDatabaseFiles(appContext)) {
      clearDatabase(file);
    }
  }

  private static void clearDatabase(File databaseFile) {
    SQLiteDatabase database = openDatabase(databaseFile);
    try {
      List<String> tables = getTableNames(database);

      for (String table : tables) {
        deleteTableContent(database, table);
      }
    } finally {
      database.close();
    }
  }

  private static List<File> getDatabaseFiles(Context context) {
    List<File> allDatabaseFiles = new ArrayList<>();
    for (String databaseName : context.databaseList()) {
      allDatabaseFiles.add(context.getDatabasePath(databaseName));
    }
    return filterUnwantedDatabaseFiles(allDatabaseFiles);
  }

  @VisibleForTesting
  static List<File> filterUnwantedDatabaseFiles(List<File> allDatabaseFiles) {
    List<File> filteredList = new ArrayList<>();
    for (File databaseFile : allDatabaseFiles) {
      if (!hasUnwantedSuffix(databaseFile)) {
        filteredList.add(databaseFile);
      }
    }
    return filteredList;
  }

  private static boolean hasUnwantedSuffix(File databaseFile) {
    String databaseName = databaseFile.getPath();
    for (String suffix : UNWANTED_FILENAME_SUFFIXES) {
      if (databaseName.endsWith(suffix)) {
        return true;
      }
    }
    return false;
  }

  private static SQLiteDatabase openDatabase(File databaseFile) {
    return SQLiteDatabase.openDatabase(
        databaseFile.getAbsolutePath(),
        null /* cursorFactory */,
        0 /* flags */);
  }

  private static List<String> getTableNames(SQLiteDatabase database) throws SQLiteException {
    Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type IN (?, ?)", new String[] { "table", "view" });
    try {
      List<String> tableNames = new ArrayList<>();
      while (cursor.moveToNext()) {
        tableNames.add(cursor.getString(0));
      }
      return tableNames;
    } finally {
      cursor.close();
    }
  }

  private static void deleteTableContent(SQLiteDatabase database, String table) {
    database.delete(table, null, null);
  }
}
