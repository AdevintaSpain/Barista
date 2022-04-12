package com.alorma.barista_compose.assertion.internal.matcher

import androidx.annotation.DrawableRes
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.ComposeTestRule

fun Modifier.setDrawableSemantic(@DrawableRes id: Int): Modifier {
  return semantics(mergeDescendants = false) {
    drawableId = id
  }
}

fun ComposeTestRule.onNodeWithDrawable(@DrawableRes id: Int) = onNode(
  SemanticsMatcher.expectValue(DrawableId, id)
)

private const val SEMANTIC_DRAWABLE_KEY = "DrawableResId"
private val DrawableId = SemanticsPropertyKey<Int>(SEMANTIC_DRAWABLE_KEY)
private var SemanticsPropertyReceiver.drawableId by DrawableId
