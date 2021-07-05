package com.adevinta.android.barista.assertion

import android.view.View
import androidx.annotation.IdRes
import com.adevinta.android.barista.assertion.BaristaAssertions.assertAny
import com.google.android.material.chip.ChipGroup

object BaristaChipAssertions {

  @JvmStatic
  fun assertAnyChipSelected(@IdRes viewId: Int) {
    assertAny<ChipGroup>(viewId) {
      it.checkedChipId != View.NO_ID
    }
  }
}