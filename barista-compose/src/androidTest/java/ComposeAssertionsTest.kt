package com.alorma.barista_compose.assertion

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import com.adevinta.android.barista.sample.TextComposable
import com.alorma.barista_compose.R
import org.junit.Rule
import org.junit.Test

class ComposeAssertionsTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun assertIsDisplayed_StringTest() {
    val text = "Hello world"
    composeTestRule.setContent { TextComposable(text = text) }

    composeTestRule.assertIsDisplayed(text = text)
  }

  @Test
  @Composable
  fun assertIsDisplayed_ResourceTest() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertIsDisplayed(text = textRes)
  }

  @Test
  fun assertIsNotDisplayed_StringTest() {
    val text = "Hello world"
    composeTestRule.setContent { TextComposable(text = text) }

    composeTestRule.assertIsNotDisplayed(text = "Hello world!")
  }

  @Test
  @Composable
  fun assertIsNotDisplayed_ResourceTest() {
    val textRes = R.string.next
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertIsNotDisplayed(text = R.string.app_name)
  }
}