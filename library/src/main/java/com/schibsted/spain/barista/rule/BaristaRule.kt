package com.schibsted.spain.barista.rule

import android.app.Activity
import android.content.Intent
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import com.schibsted.spain.barista.rule.cleardata.ClearFilesRule
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule
import com.schibsted.spain.barista.rule.flaky.FlakyTestRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class BaristaRule<T : Activity> private constructor(activityClass: Class<T>) : TestRule {

  companion object {
    private const val DEFAULT_FLAKY_ATTEMPTS = 10
    private const val LAUNCH_ACTIVITY_AUTOMATICALLY = false
    private const val INITIAL_TOUCH_MODE_ENABLED = true

    inline fun <reified T : Activity> create(): BaristaRule<T> = BaristaRule.create(T::class.java)

    @JvmStatic
    fun <T : Activity> create(activityClass: Class<T>): BaristaRule<T> {
      return BaristaRule(activityClass)
    }
  }

  private val clearPreferencesRule: ClearPreferencesRule = ClearPreferencesRule()
  private val clearDatabaseRule: ClearDatabaseRule = ClearDatabaseRule()
  private val clearFilesRule: ClearFilesRule = ClearFilesRule()
  private val flakyTestRule: FlakyTestRule = FlakyTestRule().apply {
    allowFlakyAttemptsByDefault(DEFAULT_FLAKY_ATTEMPTS)
  }
  val activityTestRule: ActivityTestRule<T> = ActivityTestRule(activityClass,
      INITIAL_TOUCH_MODE_ENABLED,
      LAUNCH_ACTIVITY_AUTOMATICALLY)

  override fun apply(base: Statement, description: Description): Statement {
    return RuleChain.outerRule(flakyTestRule)
        // ↓ All rules below flakyTestRule will be repeated
        .around(activityTestRule)
        // ↓ All rules below activityTestRule will execute before launching the activity
        .around(clearPreferencesRule)
        .around(clearDatabaseRule)
        .around(clearFilesRule)
        .apply(base, description)
  }

  fun launchActivity() {
    activityTestRule.launchActivity(null)
  }

  fun launchActivity(startIntent: Intent) {
    activityTestRule.launchActivity(startIntent)
  }
}
