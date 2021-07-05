package com.adevinta.android.barista.sample

import android.graphics.Color
import com.adevinta.android.barista.internal.util.ColorResourceType
import com.adevinta.android.barista.internal.util.colorResourceType
import junit.framework.Assert.assertEquals
import org.junit.Test

class ColorResourceTypeTest {

  @Test
  fun color_resource_type_color_res() {
    val type = R.color.blue.colorResourceType
    assertEquals(type, ColorResourceType.COLOR_RES)
  }

  @Test
  fun color_resource_type_color_attr() {
    val type = R.attr.colorPrimary.colorResourceType
    assertEquals(type, ColorResourceType.COLOR_ATTR)
  }

  @Test
  fun color_resource_type_color_int() {
    val type = Color.BLUE.colorResourceType
    assertEquals(type, ColorResourceType.COLOR_INT)
  }

  @Test
  fun color_resource_type_color_state_list() {
    val type = R.color.selector_default_disabled_checked.colorResourceType
    assertEquals(type, ColorResourceType.COLOR_RES)
  }
}