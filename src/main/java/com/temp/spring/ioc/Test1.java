package com.temp.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 AM 9:58
 * 4
 */
public class Test1 {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        BeanTest bean = context.getBean(BeanTest.class);

        System.out.println(bean.getClass());

        System.out.println(Integer.MAX_VALUE+Integer.MAX_VALUE+1);

    }
}
