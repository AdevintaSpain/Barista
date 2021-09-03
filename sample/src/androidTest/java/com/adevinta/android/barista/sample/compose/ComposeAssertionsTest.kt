package com.adevinta.android.barista.sample.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import com.adevinta.android.barista.sample.R
import com.alorma.barista_compose.assertion.assertIsDisplayed
import org.junit.Rule
import org.junit.Test

class ComposeAssertionsTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun assertTextString_Test() {
    val text = "Hello world"
    composeTestRule.setContent { TextComposable(text = text) }

    composeTestRule.assertIsDisplayed(text = text)
  }

  @Test
  @Composable
  fun assertTextResource_Test() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertIsDisplayed(text = textRes)
  }
}