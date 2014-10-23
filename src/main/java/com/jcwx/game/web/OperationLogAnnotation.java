package com.jcwx.game.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 日志操作类型注解 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogAnnotation {
    /** 描述 */
    String label();

    /** 类型 */
    int type();
}
