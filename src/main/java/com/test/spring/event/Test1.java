package com.test.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 PM 4:04
 * 4
 */
public class Test1 {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        configApplicationContext.addApplicationListener(new TestEventListener());
        TestEventPublisher bean = configApplicationContext.getBean(TestEventPublisher.class);
        bean.sendMsg("1234");


    }



}
