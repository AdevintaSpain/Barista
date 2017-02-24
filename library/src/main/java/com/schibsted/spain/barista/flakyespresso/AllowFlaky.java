package com.schibsted.spain.barista.flakyespresso;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
    java.lang.annotation.ElementType.METHOD
})
public @interface AllowFlaky {
  int attempts() default 5;
}
