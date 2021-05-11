package com.schibsted.spain.barista.rule.flaky

import com.schibsted.spain.barista.rule.flaky.internal.FlakyStatementBuilder
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * This TestRule enables repetition of flaky tests using the annotations @[AllowFlaky] and @[Repeat].
 * Check out the docs on each one to see how they behave.
 *
 * NOTE: Rule ordering is important. Use a [org.junit.rules.RuleChain] to make sure this is the outermost rule,
 * otherwise you might get unexpected results.
 */
class FlakyTestRule : TestRule {

  private val flakyStatementBuilder = FlakyStatementBuilder()

  /**
   * Utility method to use @[AllowFlaky] by default in all test methods.
   * <br></br>
   * Use this method when constructing the Rule to apply a default behavior of @[AllowFlaky] without having to add the annotation to
   * each test. Use it wisely when your tests are very flaky, something quite common with Espresso.
   * <br></br>
   * The default behavior can be overridden with [Repeat] or [AllowFlaky].
   */
  fun allowFlakyAttemptsByDefault(defaultAttempts: Int): FlakyTestRule {
    flakyStatementBuilder.allowFlakyAttemptsByDefault(defaultAttempts)
    return this
  }

  /**
   * Utility method to use @[Repeat] by default in all test methods.
   * <br></br>
   * Use this method when constructing the Rule to apply a default behavior of @[Repeat] without having to add the annotation to
   * each test. This can help you to find flaky tests.
   * <br></br>
   * The default behavior can be overridden with [Repeat] or [Repeat].
   */
  fun repeatAttemptsByDefault(defaultAttempts: Int): FlakyTestRule {
    flakyStatementBuilder.setRepeatAttemptsByDefault(defaultAttempts)
    return this
  }

  override fun apply(base: Statement, description: Description): Statement {
    return flakyStatementBuilder
        .setBase(base)
        .setDescription(description)
        .build()
  }
}
