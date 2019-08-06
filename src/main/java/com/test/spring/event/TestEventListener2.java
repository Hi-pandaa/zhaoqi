package com.test.spring.event;

import org.springframework.context.event.EventListener;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 PM 3:51
 * 4
 */

public class TestEventListener2 {


    @EventListener
    public void onListen(TestEvent testEvent) {
        System.out.println("监听到事件,msg=" + testEvent.getMsg());

    }


}
