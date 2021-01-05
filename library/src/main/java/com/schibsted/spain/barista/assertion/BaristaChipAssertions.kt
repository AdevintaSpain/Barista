package com.schibsted.spain.barista.assertion

import android.view.View
import androidx.annotation.IdRes
import com.google.android.material.chip.ChipGroup
import com.schibsted.spain.barista.assertion.BaristaAssertions.assertAny

object BaristaChipAssertions {

  @JvmStatic
  fun assertAnyChipSelected(@IdRes viewId: Int) {
    assertAny<ChipGroup>(viewId) {
      it.checkedChipId != View.NO_ID
    }
  }
}