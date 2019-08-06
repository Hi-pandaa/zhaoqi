package com.test.spring.factorybean.aop;

import com.test.spring.factorybean.aop.service.UserService;
import com.test.spring.factorybean.aop.service.impl.UserServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/31 0031 PM 4:19
 * 4
 */
@Configuration
@SpringBootApplication
public class AppConfig {


    @Bean
    public UserService userService() {

        return new UserServiceImpl();
    }

    @Bean
    public TestAop testAop() {
        return new TestAop();
    }


}
