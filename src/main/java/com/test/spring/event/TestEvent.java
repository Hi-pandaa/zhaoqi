package com.test.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 PM 3:44
 * 4
 */
public class TestEvent extends ApplicationEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public TestEvent(Object source) {
        super(source);
    }
}
