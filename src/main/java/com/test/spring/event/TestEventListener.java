package com.test.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 PM 3:45
 * 4 通过容器加入listener
 */
public class TestEventListener implements ApplicationListener<TestEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(TestEvent event) {
        System.out.println("监听到事件,msg=" + event.getMsg());
    }
}
