package com.springbootdemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 */
//方法注解
@Target(ElementType.METHOD)
//运行时可见
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    //记录日志的操作类型
    String operateType() default "";
}
