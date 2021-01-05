package com.schibsted.spain.barista.interaction

import com.schibsted.spain.barista.internal.performAction
import com.schibsted.spain.barista.internal.util.resourceMatcher
import com.schibsted.spain.barista.internal.viewaction.ChipViewActions.removeChip

object BaristaChipInteractions {

  @JvmStatic
  fun closeChip(resId: Int) {
    resId.resourceMatcher().performAction(removeChip())
  }
}
