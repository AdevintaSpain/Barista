package com.adevinta.android.barista.interaction

import com.adevinta.android.barista.internal.performAction
import com.adevinta.android.barista.internal.util.resourceMatcher
import com.adevinta.android.barista.internal.viewaction.ChipViewActions.removeChip

object BaristaChipInteractions {

  @JvmStatic
  fun closeChip(resId: Int) {
    resId.resourceMatcher().performAction(removeChip())
  }
}
