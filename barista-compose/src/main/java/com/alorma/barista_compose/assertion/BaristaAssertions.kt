package com.alorma.barista_compose.assertion

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import com.alorma.barista_compose.assertion.internal.resources

fun ComposeTestRule.assertIsDisplayed(
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

fun ComposeTestRule.assertIsDisplayed(
  useUnmergedTree: Boolean = false,
  @StringRes textRes: Int,
  substring: Boolean = false,
  ignoreCase: Boolean = false
): SemanticsNodeInteraction {
  return assertIsDisplayed(useUnmergedTree, resources().getString(textRes), substring, ignoreCase)
}

fun ComposeTestRule.assertIsNotDisplayed(
  useUnmergedTree: Boolean = false,
  text: String,
  substring: Boolean = false,
  ignoreCase: Boolean = false
): SemanticsNodeInteraction {
  return onRoot(useUnmergedTree = useUnmergedTree)
    .assert(
      hasText(
        text = text,
        substring = substring,
        ignoreCase = ignoreCase,
      ).not()
    )
}

fun ComposeTestRule.assertIsNotDisplayed(
  useUnmergedTree: Boolean = false,
  @StringRes textRes: Int,
  substring: Boolean = false,
  ignoreCase: Boolean = false
): SemanticsNodeInteraction {
  return assertIsNotDisplayed(useUnmergedTree, resources().getString(textRes), substring, ignoreCase)
}