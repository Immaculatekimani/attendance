package com.emma.app.view.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BackgroundColor {
    String value() default ""; // Default background color

    String presentColor() default "7ED7C1"; // Color for "Present" status

    String lateColor() default "darkorange"; // Color for "Late" status

    String absentColor() default "orangered";

    String halfdayColor() default "pink";

    String fulldayColor() default "7ED7C1";

    String inTimeColor() default "D6D46D";

    String joiningLateColor() default "EE5A5A";

    String nullColor() default "darkslategray";


    // C
}
