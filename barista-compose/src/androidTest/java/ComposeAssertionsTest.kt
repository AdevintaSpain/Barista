package com.alorma.barista_compose.assertion

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.adevinta.android.barista.sample.TextComposable
import com.adevinta.android.barista.sample.WrappedTextComposable
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

  @Test
  fun onNode_assertIsDisplayed_StringTest() {
    val text = "Barista"
    composeTestRule.setContent {
      WrappedTextComposable(text = text, nodeTestTag = "Node test")
    }

    composeTestRule.onNodeWithTag("Node test").assertIsDisplayed(text = "Barista")
  }

  @Test(expected = AssertionError::class)
  fun onNode_assertIsDisplayed_StringTest_fail_nodeNotFound() {
    val text = "Barista"
    composeTestRule.setContent {
      WrappedTextComposable(text = text, nodeTestTag = "Node test")
    }

    composeTestRule.onNodeWithTag("Node test X").assertIsDisplayed(text = "Barista")
  }

  @Test(expected = AssertionError::class)
  fun onNode_assertIsDisplayed_StringTest_fail() {
    val text = "Barista"
    composeTestRule.setContent {
      WrappedTextComposable(text = text, nodeTestTag = "Node test")
    }

    composeTestRule.onNodeWithTag("Node test").assertIsDisplayed(text = "Barista Compose")
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

  @Test(expected = AssertionError::class)
  fun onNode_assertIsNotDisplayed_StringTest_fail_nodeNotFound() {
    val text = "Barista"
    composeTestRule.setContent {
      WrappedTextComposable(text = text, nodeTestTag = "Node test")
    }

    composeTestRule.onNodeWithTag("Node test X").assertIsNotDisplayed(text = "Barista Compose")
  }

  @Test(expected = AssertionError::class)
  fun onNode_assertIsNotDisplayed_StringTest_fail() {
    val text = "Barista"
    composeTestRule.setContent {
      WrappedTextComposable(text = text, nodeTestTag = "Node test")
    }

    composeTestRule.onNodeWithTag("Node test").assertIsNotDisplayed(text = "Barista")
  }
}