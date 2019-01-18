package com.schibsted.spain.barista.rule.cleardata.internal

import android.database.sqlite.SQLiteDatabase
import android.support.test.InstrumentationRegistry
import java.io.File
import java.util.ArrayList

class DatabaseOperations {

  fun getAllDatabaseFiles(): List<File> {
    return InstrumentationRegistry.getTargetContext()
        .let { context ->
          context.databaseList()
              .map { context.getDatabasePath(it) }

        }
  }

  fun openDatabase(databaseFile: File): SQLiteDatabase {
    return SQLiteDatabase.openDatabase(
        databaseFile.absolutePath,
        null /* cursorFactory */,
        0 /* flags */)
  }

  fun getTableNames(sqLiteDatabase: SQLiteDatabase): List<String> {
    sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type IN (?, ?)", arrayOf("table", "view"))
        .use { cursor ->
          val tableNames = ArrayList<String>()
          while (cursor.moveToNext()) {
            tableNames.add(cursor.getString(0))
          }
          return tableNames
        }
  }

  fun deleteTableContent(sqLiteDatabase: SQLiteDatabase, tableName: String) {
    sqLiteDatabase.delete(tableName, null, null)
  }
}