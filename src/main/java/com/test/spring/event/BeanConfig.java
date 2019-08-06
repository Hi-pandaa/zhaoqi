package com.test.spring.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 PM 4:05
 * 4
 */
@Configuration
public class BeanConfig {

    @Bean
    public TestEventPublisher testEventPublisher() {
        return new TestEventPublisher();
    }

    @Bean
    public TestEventListener2 testEventListener2() {
        return new TestEventListener2();
    }


}
