package com.alorma.barista_compose.assertion

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onRoot

fun ComposeTestRule.assertIsDisplayed(
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
      )
    )
}

@Composable
fun ComposeTestRule.assertIsDisplayed(
  useUnmergedTree: Boolean = false,
  @StringRes text: Int,
  substring: Boolean = false,
  ignoreCase: Boolean = false
): SemanticsNodeInteraction {
  return onRoot(useUnmergedTree = useUnmergedTree)
    .assert(
      hasText(
        text = stringResource(text),
        substring = substring,
        ignoreCase = ignoreCase,
      )
    )
}