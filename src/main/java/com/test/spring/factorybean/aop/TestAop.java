package com.test.spring.factorybean.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/31 0031 PM 4:08
 * 4
 */
@Component
@Order(-10000)
@Aspect()
public class TestAop {

    @Pointcut("@annotation(com.test.spring.factorybean.aop.AopAnnotation)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object invoke(ProceedingJoinPoint joinPoint) {

        System.out.println("@in 进入切面");
        Object res = null;
        try {
            res = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {

        }
        System.out.println("@out 出切面");

        return res;
    }
}
