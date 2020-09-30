package com.schibsted.spain.barista.rule.theme

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DayNightStatement(
  private val base: Statement,
  private val description: Description,
  private val modes: List<Int>
) : Statement() {

  override fun evaluate() {
    modes.forEach { mode ->
      Handler(Looper.getMainLooper()).post {
        AppCompatDelegate.setDefaultNightMode(mode)
      }
      try {
        base.evaluate()
      } catch (throwable: Throwable) {
        val testName = "${description.testClass.simpleName}\$${description.methodName}"
        val errorMessage = "Test $testName failed on DayNight mode: [${getDayNightModeName(mode)}]"
        Log.e(TAG, errorMessage)
        throw throwable
      }
    }
  }

  private fun getDayNightModeName(mode: Int): String = when (mode) {
    AppCompatDelegate.MODE_NIGHT_AUTO -> "MODE_NIGHT_AUTO"
    AppCompatDelegate.MODE_NIGHT_NO -> "MODE_NIGHT_NO"
    AppCompatDelegate.MODE_NIGHT_YES -> "MODE_NIGHT_YES"
    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> "MODE_NIGHT_FOLLOW_SYSTEM"
    else -> "Unknown"
  }

  companion object {
    private const val TAG = "DAY_NIGHT"
  }
}