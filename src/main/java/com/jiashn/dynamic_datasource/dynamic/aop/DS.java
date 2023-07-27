package com.jiashn.dynamic_datasource.dynamic.aop;

import java.lang.annotation.*;

/**
 * @author: jiangjs
 * @description:
 * @date: 2023/7/27 14:39
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DS {
    String value() default "master";
}
