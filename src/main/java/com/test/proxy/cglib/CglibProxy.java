package com.test.proxy.cglib;

import com.test.proxy.model.User;
import com.test.proxy.model.UserImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/22 0022 PM 7:56
 * 4
 */
public class CglibProxy {


    public static void main(String[] args) {


        Enhancer enhancer = new Enhancer();
        //设置被代理对象的类  可以没有接口 但是不能被final 修饰
        enhancer.setSuperclass(UserImpl.class);
        //设置回调处理方法
        enhancer.setCallback(new CglibInterceptor());
        //创建代理对象
        UserImpl userProxy = (UserImpl) enhancer.create();
        System.out.println("生成的代理对象为="+userProxy.getClass());

        userProxy.doRegistry();
    }
}
