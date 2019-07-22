package com.test.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/22 0022 PM 7:51
 * 4 生成代理的拦截处理类
 * 并非是生成的代理类
 */
public class CglibInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>>>>>>>拦截方法前,被代理的类="+o.getClass());
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println(">>>>>>>>>>>>>>>>>>>>拦截方法后");

        return invoke;
    }
}
