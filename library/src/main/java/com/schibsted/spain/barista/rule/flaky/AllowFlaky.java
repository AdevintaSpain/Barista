package com.schibsted.spain.barista.rule.flaky;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * It let's flaky tests pass.
 * <br>
 * This annotation together with {@link FlakyActivityTestRule} repeats the annotated test several times, and makes the test pass if any of
 * the executions passed. It's the opposite of {@link Repeat}.
 * <br>
 * You can use {@link FlakyActivityTestRule#allowFlakyAttemptsByDefault(int)} instead.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
    java.lang.annotation.ElementType.METHOD
})
public @interface AllowFlaky {
  int attempts() default 5;
}
