package com.schibsted.spain.barista.sample

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.util.Scanner

class FileActivity : Activity() {
  private var currentValueText: TextView? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_file)
    currentValueText = findViewById<View>(R.id.file_current_value) as TextView
    findViewById<View>(R.id.file_increment_button).setOnClickListener {
      incrementValue()
      showCurrentValue()
    }
    showCurrentValue()
  }

  private fun showCurrentValue() {
    currentValueText!!.text = oldValue.toString()
  }

  private fun incrementValue() {
    try {
      val oldValue = oldValue
      val incrementedValue = oldValue + 1
      directory.mkdir()
      val out: Writer = OutputStreamWriter(FileOutputStream(file))
      out.write("" + incrementedValue)
      out.close()
    } catch (ignore: IOException) {
      Log.e("ERROR", "Error saving the File", ignore)
    }
  }

  private val oldValue: Int
    private get() {
      val file = file
      return try {
        var readValue = 0
        val scanner = Scanner(FileInputStream(file))
        while (scanner.hasNextLine()) {
          readValue = scanner.nextLine().toInt()
        }
        scanner.close()
        readValue
      } catch (notFound: FileNotFoundException) {
        0
      }
    }
  private val file: File
    private get() = File(directory, SAMPLE_FILE_NAME)
  private val directory: File
    private get() {
      val path = intent.getStringExtra(EXTRA_PATH)
      return File(this.filesDir.toString() + path)
    }

  companion object {
    const val EXTRA_PATH = "path"
    private const val SAMPLE_FILE_NAME = "sample.txt"
  }
}