package io.github.akjo03.lib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
@SuppressWarnings("unused")
public @interface PotentiallyDangerous {
    String value() default "This method is potentially dangerous and should be used with caution.";
}