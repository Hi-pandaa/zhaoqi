package com.test.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/22 0022 AM 11:01
 * 4 jdk代理的动态处理类
 */
public class JdkInvocationHandler implements InvocationHandler {

    private Object object;

    public JdkInvocationHandler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>>>>>>>@in切面>>>>>>>>>>>>>>>>>>>>,代理类名称为" + proxy.getClass().getName());
        Object invoke = method.invoke(object, args);
        System.out.println(">>>>>>>>>>>>>>>>>>>>@out切面>>>>>>>>>>>>>>>>>>>>");
        return invoke;
    }
}
