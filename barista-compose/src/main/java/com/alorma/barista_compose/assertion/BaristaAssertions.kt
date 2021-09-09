package com.alorma.barista_compose.assertion

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import com.alorma.barista_compose.assertion.internal.resources

fun ComposeTestRule.assertDisplayed(
  useUnmergedTree: Boolean = false,
  text: String,
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
  useUnmergedTree: Boolean = false,
  @StringRes textRes: Int,
  substring: Boolean = false,
  ignoreCase: Boolean = false,
): SemanticsNodeInteraction {
  return assertDisplayed(useUnmergedTree, resources().getString(textRes), substring, ignoreCase)
}

fun ComposeTestRule.assertNotDisplayed(
  useUnmergedTree: Boolean = false,
  text: String,
  substring: Boolean = false,
  ignoreCase: Boolean = false
) {
  return onNodeWithText(text, substring, ignoreCase, useUnmergedTree).assertDoesNotExist()
}

fun ComposeTestRule.assertNotDisplayed(
  useUnmergedTree: Boolean = false,
  @StringRes textRes: Int,
  substring: Boolean = false,
  ignoreCase: Boolean = false
) {
  return assertNotDisplayed(useUnmergedTree, resources().getString(textRes), substring, ignoreCase)
}