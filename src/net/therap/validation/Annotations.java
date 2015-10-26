package net.therap.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author babar
 * @since 10/26/15
 */

@Retention(RetentionPolicy.RUNTIME)
@interface Name {
    public String name() default "name";
}

@Retention(RetentionPolicy.RUNTIME)
@interface Email {
    public String name() default "email";
}

@Retention(RetentionPolicy.RUNTIME)
@interface Age {
    public String name() default "age";
}

@Retention(RetentionPolicy.RUNTIME)
@interface Salary {
    public String name() default "salary";
}

@Retention(RetentionPolicy.RUNTIME)
@interface Mobile {
    public String name() default "mobile";
}