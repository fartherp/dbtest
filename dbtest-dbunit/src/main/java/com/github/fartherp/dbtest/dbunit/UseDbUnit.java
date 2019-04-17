/*
 * Copyright (c) 2017. juzhen.io. All rights reserved.
 */

package com.github.fartherp.dbtest.dbunit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 * Author: CK
 * Date: 2016/2/4
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseDbUnit {
    String value() default "";

    String[] tables() default {};

    String fileType() default "csv";
}
