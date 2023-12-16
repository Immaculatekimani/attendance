package com.emma.app.view.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyHtmlForm {
    String label();

    String url();

    String httpMethod() default "POST";

    String editLabel() default "";

    String editURL() default "";
    String editSubmit() default "";
    String deleteURL() default "";


}
