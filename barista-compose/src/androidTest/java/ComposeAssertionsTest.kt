package com.alorma.barista_compose.assertion

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import com.adevinta.android.barista.sample.TextComposable
import com.alorma.barista_compose.R
import org.junit.Rule
import org.junit.Test

class ComposeAssertionsTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  // assertIsDisplayed tests
  @Test
  fun assertIsDisplayed_StringTest() {
    val text = "Hello world"
    composeTestRule.setContent {
      TextComposable(text = text)
    }

    composeTestRule.assertIsDisplayed(text = text)
  }

  @Test
  fun assertIsDisplayed_ResourceTest() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertIsDisplayed(textRes = R.string.app_name)
  }

  @Test(expected = AssertionError::class)
  fun assertIsDisplayed_ResourceTest_fail() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertIsDisplayed(textRes = R.string.next)
  }

  // assertIsNotDisplayed tests
  @Test
  fun assertIsNotDisplayed_StringTest() {
    val text = "Hello world"
    composeTestRule.setContent { TextComposable(text = text) }

    composeTestRule.assertIsNotDisplayed(text = "next")
  }

  @Test
  fun assertIsNotDisplayed_ResourceTest() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertIsNotDisplayed(textRes = R.string.next)
  }

  @Test(expected = AssertionError::class)
  fun assertIsNotDisplayed_ResourceTest_fail() {
    val textRes = R.string.next
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertIsNotDisplayed(textRes = R.string.next)
  }
}