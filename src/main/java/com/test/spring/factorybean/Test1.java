package com.test.spring.factorybean;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/7/29 0029 PM 3:31
 * 4
 */
public class Test1 {


    public static void main(String[] args) {


  /*      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        //获取实例bean
        Object factoryBeanTest = context.getBean("factoryBeanTest");
        System.out.println(factoryBeanTest);

        //获取实例bean的factoryBean对象
        Object bean = context.getBean("&factoryBeanTest");
        System.out.println(bean);

*/

        /**
         * 。。。1，1，2，3，7，22
         */
        Test1.test1(10);


    }

    public static void test1(int n) {

        int count=1;

        int start = 0;
        int next = 0;

        do {
            int temp = next;
            //后面的next=start*next+1
            next = start * next + 1;
            //start 要使用next
            start = temp;
            System.out.println(next);

        } while (++count <= n);


    }
}
