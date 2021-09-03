package com.adevinta.android.barista.sample.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextComposable(
  text: String
) {
  Box(modifier = Modifier.padding(16.dp)) {
    Text(text = text)
  }
}