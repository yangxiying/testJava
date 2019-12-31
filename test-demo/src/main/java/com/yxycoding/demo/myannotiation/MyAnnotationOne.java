package com.yxycoding.demo.myannotiation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyAnnotationOne {
    String name();
    int age() default 22;
    int[] arr();
}
