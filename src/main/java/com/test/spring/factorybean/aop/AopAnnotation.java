package com.test.spring.factorybean.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/31 0031 PM 4:16
 * 4
 */
@Inherited
@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopAnnotation {
}
