package com.schibsted.spain.barista.rule

import android.app.Activity
import android.content.Intent
import android.support.test.rule.ActivityTestRule
import com.schibsted.spain.barista.rule.cleardata.ClearDatabaseRule
import com.schibsted.spain.barista.rule.cleardata.ClearFilesRule
import com.schibsted.spain.barista.rule.cleardata.ClearPreferencesRule
import com.schibsted.spain.barista.rule.flaky.FlakyTestRule
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class BaristaRule<T : Activity> private constructor(activityClass: Class<T>) : TestRule {

    private val clearPreferencesRule: ClearPreferencesRule = ClearPreferencesRule()
    private val clearDatabaseRule: ClearDatabaseRule = ClearDatabaseRule()
    private val clearFilesRule: ClearFilesRule = ClearFilesRule()
    private val flakyTestRule: FlakyTestRule
    private val activityTestRule: ActivityTestRule<T>

    init {
        this.flakyTestRule = FlakyTestRule()
                .allowFlakyAttemptsByDefault(DEFAULT_FLAKY_ATTEMPTS)
        this.activityTestRule = ActivityTestRule(activityClass,
                INITIAL_TOUCH_MODE_ENABLED,
                LAUNCH_ACTIVITY_AUTOMATICALLY)
    }

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

    companion object {
        private val DEFAULT_FLAKY_ATTEMPTS = 10
        private val LAUNCH_ACTIVITY_AUTOMATICALLY = false
        private val INITIAL_TOUCH_MODE_ENABLED = true

        @JvmStatic
        fun <T : Activity> create(activityClass: Class<T>): BaristaRule<T> {
            return BaristaRule(activityClass)
        }
    }
}
