package com.alorma.barista_compose.assertion

import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithText
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
  ignoreCase: Boolean = false,
): SemanticsNodeInteraction {
  return assertIsDisplayed(useUnmergedTree, resources().getString(textRes), substring, ignoreCase)
}

fun SemanticsNodeInteraction.assertIsDisplayed(
  text: String,
  substring: Boolean = false,
  ignoreCase: Boolean = false,
): SemanticsNodeInteractionCollection {
  return assertIsDisplayed().onChildren().assertAny(hasText(text, substring, ignoreCase))
}

fun ComposeTestRule.assertIsNotDisplayed(
  useUnmergedTree: Boolean = false,
  text: String,
  substring: Boolean = false,
  ignoreCase: Boolean = false
) {
  return onNodeWithText(text, substring, ignoreCase, useUnmergedTree).assertDoesNotExist()
}

fun ComposeTestRule.assertIsNotDisplayed(
  useUnmergedTree: Boolean = false,
  @StringRes textRes: Int,
  substring: Boolean = false,
  ignoreCase: Boolean = false
) {
  return assertIsNotDisplayed(useUnmergedTree, resources().getString(textRes), substring, ignoreCase)
}

fun SemanticsNodeInteraction.assertIsNotDisplayed(
  text: String,
  substring: Boolean = false,
  ignoreCase: Boolean = false,
): SemanticsNodeInteractionCollection {
  return assertIsDisplayed().onChildren().assertAny(!hasText(text, substring, ignoreCase))
}
