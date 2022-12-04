package com.adevinta.android.barista.compose.assertion.internal

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule

internal fun ComposeTestRule.resources() = if (this is AndroidComposeTestRule<*, *>) {
  activity.resources
} else {
  throw RuntimeException("ComposeTestRule is not AndroidComposeTestRule")
}