package com.schibsted.spain.barista.sample

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.View
import android.widget.TextView

class DatabaseActivity : Activity() {
  private var currentValueText: TextView? = null
  private var databaseOpenHelper: DatabaseOpenHelper? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_database)
    databaseOpenHelper = DatabaseOpenHelper(this)
    currentValueText = findViewById<View>(R.id.database_current_value) as TextView
    findViewById<View>(R.id.database_increment_button).setOnClickListener {
      incrementValue()
      showCurrentValue()
    }
    showCurrentValue()
  }

  private fun showCurrentValue() {
    val readableDatabase = databaseOpenHelper!!.readableDatabase
    val cursor = readableDatabase.query("User", arrayOf("name"), null, null, null, null, null)
    val currentValue = cursor.count
    currentValueText!!.text = currentValue.toString()
    cursor.close()
    readableDatabase.close()
  }

  private fun incrementValue() {
    val writableDatabase = databaseOpenHelper!!.writableDatabase
    val contentValues = ContentValues(1)
    contentValues.put("name", "Mr T")
    writableDatabase.insert("User", null, contentValues)
    writableDatabase.close()
  }

  private class DatabaseOpenHelper internal constructor(context: Context?) : SQLiteOpenHelper(context, "mydatabase.db", null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
      db.execSQL("CREATE TABLE User (name TEXT NOT NULL);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
      private const val DB_VERSION = 1
    }
  }
}