package com.test.spring.factorybean.aop;

import com.alibaba.fastjson.JSONObject;
import com.test.spring.factorybean.aop.service.UserService;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/31 0031 PM 4:20
 * 4
 */
public class Test1 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService service = applicationContext.getBean("userService",UserService.class);
        System.out.println(service.getClass());


        List<Method> methods = getObjMethods(service);
        for(Method method:methods){
            System.out.println(method.getName());
            if(method.getName().equalsIgnoreCase("test1")){
                System.out.println("----------------");
            }
        }

        System.out.println("methonds="+ JSONObject.toJSONString(methods));

        System.out.println(">>>>>>>>>>");
        service.test1();
    }

    /**
     * 获取对象obj的所有方法
     *
     * @param object
     * @return
     */
    public static List<Method> getObjMethods(Object object) {

        List<Method> methods = new LinkedList<Method>();
        for (Class<? extends Object> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {


                for (Method method : clazz.getDeclaredMethods()) {
                    methods.add(method);
                }
            } catch (Exception localException) {
                clazz = clazz.getSuperclass();
            }
        }
        return methods;
    }

}
