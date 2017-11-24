package com.schibsted.spain.barista.rule.flaky;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * It avoids flaky test from passing.
 * <br>
 * This annotation together with {@link FlakyTestRule} repeats the annotated test several times, and makes the test fail if any of
 * the executions failed. It's the opposite of {@link AllowFlaky}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
    java.lang.annotation.ElementType.METHOD
})
public @interface Repeat {
  int times() default 5;
}
