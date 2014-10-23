package com.jcwx.game.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 通用注解 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheAnnotation {

    /** 描述 */
    String label() default "";

    /** 缓存名称 */
    String name() default "";

}
