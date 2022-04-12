package com.alorma.barista_compose.assertion

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import com.adevinta.android.barista.sample.TextComposable
import com.alorma.barista_compose.R
import com.alorma.barista_compose.assertion.internal.matcher.onNodeWithDrawable
import com.alorma.barista_compose.assertion.internal.matcher.setDrawableSemantic
import org.junit.Rule
import org.junit.Test

class ComposeAssertionsTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  // assertDisplayed tests
  @Test
  fun assertDisplayed_StringTest() {
    composeTestRule.setContent {
      TextComposable("Hello world")
    }

    composeTestRule.assertDisplayed("Hello world")
  }

  @Test
  fun assertDisplayed_ResourceTest() {
    composeTestRule.setContent {
      TextComposable(stringResource(R.string.app_name))
    }

    composeTestRule.assertDisplayed(R.string.app_name)
  }

  @Test(expected = AssertionError::class)
  fun assertDisplayed_ResourceTest_fail() {
    composeTestRule.setContent {
      TextComposable(stringResource(R.string.app_name))
    }

    composeTestRule.assertDisplayed(R.string.next)
  }

  // assertNotDisplayed tests
  @Test
  fun assertNotDisplayed_StringTest() {
    val text = "Hello world"
    composeTestRule.setContent { TextComposable(text) }

    composeTestRule.assertNotDisplayed("next")
  }

  @Test
  fun assertNotDisplayed_ResourceTest() {
    composeTestRule.setContent {
      TextComposable(stringResource(R.string.app_name))
    }

    composeTestRule.assertNotDisplayed(R.string.next)
  }

  @Test(expected = AssertionError::class)
  fun assertNotDisplayed_ResourceTest_fail() {
    composeTestRule.setContent {
      TextComposable(stringResource(R.string.next))
    }

    composeTestRule.assertNotDisplayed(R.string.next)
  }

  @Test
  fun assertDisplayed_DrawableTest() {
    composeTestRule.setContent {
      IconButton(onClick = { }) {
        Image(
          painter = painterResource(id = R.drawable.barista_logo_vector),
          modifier = Modifier
            .size(24.dp)
            .setDrawableSemantic(R.drawable.barista_logo_vector),
          contentDescription = null
        )
      }
    }

    composeTestRule.onNodeWithDrawable(R.drawable.barista_logo_vector).assertIsDisplayed()
  }
}
