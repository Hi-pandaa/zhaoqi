package com.test.proxy.jdk;

import com.test.proxy.model.User;
import com.test.proxy.model.UserImpl;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.Proxy;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/22 0022 AM 11:24
 * 4
 */
public class JdkProxy {


    public static void main(String[] args) {
        //获取当前类的类加载器
        ClassLoader loader = JdkProxy.class.getClassLoader();


        //创建一个要被代理的代理对象
        User user = new UserImpl();
        //创建处理增强类的处理方法
        JdkInvocationHandler jdkInvocationHandler = new JdkInvocationHandler(user);
        //获取要被代理的对象  的所有方法
        Class<?>[] interfaces = user.getClass().getInterfaces();
        //创建增强类
        User proxyService =(User) Proxy.newProxyInstance(loader, interfaces, jdkInvocationHandler);
        //这个增强类是Proxy的字段 是User的实现类
        if (proxyService instanceof User) {

            System.out.println("为User的的实现类");
        }
        if (proxyService instanceof Proxy) {
            System.out.println("为Proxy的的实现类");
        }
        //执行增强类的方法
        proxyService.doRegistry();



    }
}
