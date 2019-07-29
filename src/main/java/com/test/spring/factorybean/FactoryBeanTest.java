package com.test.spring.factorybean;

import com.test.spring.factorybean.model.BeanTest;
import org.springframework.beans.factory.FactoryBean;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/29 0029 PM 2:58
 * 4 用来测试FactoryBean
 */
public class FactoryBeanTest implements FactoryBean<BeanTest> {

    @Override
    public BeanTest getObject() throws Exception {
        return new BeanTest();
    }

    @Override
    public Class<?> getObjectType() {
        return BeanTest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
