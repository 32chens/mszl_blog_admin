package com.chenlf.blog.common.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheAnnotation {

    long expire() default 1 * 60 * 1000;

    String name();
}
