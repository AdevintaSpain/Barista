package com.adevinta.android.barista.rule.theme

import androidx.appcompat.app.AppCompatDelegate
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DayNightRule(private val modes: List<Int> = DAY_NIGHT_MODES) : TestRule {

  override fun apply(base: Statement, description: Description): Statement {
    return DayNightStatement(base, description, modes)
  }

  companion object {
    private val DAY_NIGHT_MODES: List<Int> = listOf(
      AppCompatDelegate.MODE_NIGHT_NO,
      AppCompatDelegate.MODE_NIGHT_YES
    )
  }
}