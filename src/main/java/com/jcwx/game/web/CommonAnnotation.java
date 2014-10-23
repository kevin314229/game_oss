package com.jcwx.game.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 通用注解 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonAnnotation {

    /** 描述 */
    String label() default "";

    /** 类型 */
    int type() default 0;

    /** 数值 */
    int val() default 0;

    /** 数值2 */
    int val2() default 0;

}
