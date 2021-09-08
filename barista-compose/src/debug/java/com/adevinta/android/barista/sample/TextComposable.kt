package com.adevinta.android.barista.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp

@Composable
fun TextComposable(
  text: String
) {
  Box(modifier = Modifier.padding(16.dp)) {
    Text(text = text)
  }
}

@Composable
fun WrappedTextComposable(
  text: String,
  nodeTestTag: String,
) {
  Column {
    Text("Hello world")
    Spacer(modifier = Modifier.height(8.dp))
    Box(modifier = Modifier
      .padding(16.dp)
      .semantics {
        testTag = nodeTestTag
      }) {
      Text(text = text)
    }
  }
}