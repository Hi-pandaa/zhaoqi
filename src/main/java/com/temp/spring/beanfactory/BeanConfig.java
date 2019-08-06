package com.temp.spring.beanfactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 AM 9:57
 * 4
 */
@Configuration
public class BeanConfig {

    @Bean
    public BeanTest beanTest() {
        return new BeanTest();
    }


}
