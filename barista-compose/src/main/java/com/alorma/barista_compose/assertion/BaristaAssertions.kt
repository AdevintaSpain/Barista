package com.alorma.barista_compose.assertion

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import com.alorma.barista_compose.assertion.internal.resources

fun ComposeTestRule.assertDisplayed(
  text: String,
  useUnmergedTree: Boolean = false,
  substring: Boolean = false,
  ignoreCase: Boolean = false
): SemanticsNodeInteraction {
  return onNodeWithText(
    text = text,
    substring = substring,
    ignoreCase = ignoreCase,
    useUnmergedTree = useUnmergedTree
  ).assertIsDisplayed()
}

fun ComposeTestRule.assertDisplayed(
  @StringRes textRes: Int,
  useUnmergedTree: Boolean = false,
  substring: Boolean = false,
  ignoreCase: Boolean = false,
): SemanticsNodeInteraction {
  return assertDisplayed(resources().getString(textRes), useUnmergedTree, substring, ignoreCase)
}

fun ComposeTestRule.assertNotDisplayed(
  text: String,
  useUnmergedTree: Boolean = false,
  substring: Boolean = false,
  ignoreCase: Boolean = false
) {
  return onNodeWithText(text, substring, ignoreCase, useUnmergedTree).assertDoesNotExist()
}

fun ComposeTestRule.assertNotDisplayed(
  @StringRes textRes: Int,
  useUnmergedTree: Boolean = false,
  substring: Boolean = false,
  ignoreCase: Boolean = false
) {
  return assertNotDisplayed(resources().getString(textRes), useUnmergedTree, substring, ignoreCase)
}