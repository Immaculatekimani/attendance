package com.emma.app.view.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyHtmlFormField {
    String id() default "";

    String name() default "";

    String label() default "";

    String labelFor() default "";

    String placeholder() default "";

    String styleClass() default "";
}
