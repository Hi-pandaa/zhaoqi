package com.test.spring.factorybean.aop.service.impl;

import com.test.spring.factorybean.aop.AopAnnotation;
import com.test.spring.factorybean.aop.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/31 0031 PM 4:18
 * 4
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    @AopAnnotation
    public    void test1() {
        System.out.println("执行test1方法");
        System.out.println(this.getClass());
        this.test2();
    }

    @AopAnnotation
      void test2() {
        System.out.println("执行test2方法");
        System.out.println(this.getClass());


    }
}
