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

  // assertDisplayed tests
  @Test
  fun assertDisplayed_StringTest() {
    val text = "Hello world"
    composeTestRule.setContent {
      TextComposable(text = text)
    }

    composeTestRule.assertDisplayed(text = text)
  }

  @Test
  fun assertDisplayed_ResourceTest() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertDisplayed(textRes = R.string.app_name)
  }

  @Test(expected = AssertionError::class)
  fun assertDisplayed_ResourceTest_fail() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertDisplayed(textRes = R.string.next)
  }

  // assertNotDisplayed tests
  @Test
  fun assertNotDisplayed_StringTest() {
    val text = "Hello world"
    composeTestRule.setContent { TextComposable(text = text) }

    composeTestRule.assertNotDisplayed(text = "next")
  }

  @Test
  fun assertNotDisplayed_ResourceTest() {
    val textRes = R.string.app_name
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertNotDisplayed(textRes = R.string.next)
  }

  @Test(expected = AssertionError::class)
  fun assertNotDisplayed_ResourceTest_fail() {
    val textRes = R.string.next
    composeTestRule.setContent {
      val text = stringResource(id = textRes)
      TextComposable(text = text)
    }

    composeTestRule.assertNotDisplayed(textRes = R.string.next)
  }
}