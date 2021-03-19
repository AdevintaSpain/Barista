package com.schibsted.spain.barista.sample

import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class WrappedEditTextActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_wrapped_edittext)
    val searchResult = findViewById<TextView>(R.id.searchResult)
    val searchView = findViewById<SearchView>(R.id.searchview)
    val supportSearchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.supportSearchView)
    applyListener(searchResult, searchView)
    applySupportListener(searchResult, supportSearchView)
    applyAdapter(searchView)
    applySupportAdapter(supportSearchView)
    val textInputLayout = findViewById<TextInputLayout>(R.id.textInput)
    applyTextInputListener(searchResult, textInputLayout)
  }

  private fun applyListener(searchResult: TextView, searchView: SearchView) {
    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        searchResult.text = query
        return true
      }

      override fun onQueryTextChange(newText: String): Boolean {
        searchResult.text = newText
        return true
      }
    })
  }

  private fun applySupportListener(searchResult: TextView, searchView: androidx.appcompat.widget.SearchView) {
    searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        searchResult.text = query
        return true
      }

      override fun onQueryTextChange(newText: String): Boolean {
        searchResult.text = newText
        return true
      }
    })
  }

  private fun applyTextInputListener(searchResult: TextView, textInputLayout: TextInputLayout) {
    val editText = textInputLayout.editText
    editText?.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
      override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        searchResult.text = charSequence
      }

      override fun afterTextChanged(editable: Editable) {}
    })
  }

  private fun applyAdapter(searchView: SearchView) {
    val cursor = generateCursor()
    val adapter: CursorAdapter = CustomAdapter(this, cursor, true)
    searchView.suggestionsAdapter = adapter
  }

  private fun applySupportAdapter(searchView: androidx.appcompat.widget.SearchView) {
    val cursor = generateCursor()
    val adapter: androidx.cursoradapter.widget.CursorAdapter = CustomSupportAdapter(this, cursor, true)
    searchView.suggestionsAdapter = adapter
  }

  private fun generateCursor(): MatrixCursor {
    val cursor = MatrixCursor(arrayOf("_id", "name"))
    cursor.addRow(arrayOf(0, "a"))
    cursor.addRow(arrayOf(1, "b"))
    return cursor
  }

  private class CustomAdapter internal constructor(context: Context?, c: Cursor?, autoRequery: Boolean) :
    CursorAdapter(context, c, autoRequery) {
    override fun newView(context: Context, cursor: Cursor, viewGroup: ViewGroup): View {
      return TextView(context)
    }

    override fun bindView(view: View, context: Context, cursor: Cursor) {
      if (view is TextView) {
        view.text = cursor.getString(1)
      }
    }
  }

  private class CustomSupportAdapter internal constructor(context: Context?, c: Cursor?, autoRequery: Boolean) :
    androidx.cursoradapter.widget.CursorAdapter(context, c, autoRequery) {
    override fun newView(context: Context, cursor: Cursor, viewGroup: ViewGroup): View {
      return TextView(context)
    }

    override fun bindView(view: View, context: Context, cursor: Cursor) {
      if (view is TextView) {
        view.text = cursor.getString(1)
      }
    }
  }
}