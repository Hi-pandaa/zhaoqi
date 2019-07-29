package com.test.spring.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/29 0029 PM 3:37
 * 4
 */
@Configuration
public class BeanConfig {



    @Bean
    public FactoryBeanTest factoryBeanTest(){
        return new FactoryBeanTest();
    }
}
