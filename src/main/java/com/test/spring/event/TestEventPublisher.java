package com.test.spring.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * 2 * @Author: zhaoqi
 * 3 * @Date: 2019/8/6 0006 PM 3:54
 * 4
 */
public class TestEventPublisher implements ApplicationEventPublisherAware {

    public ApplicationEventPublisher publisher;



    /**
     * Set the ApplicationEventPublisher that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's afterPropertiesSet or a custom init-method.
     * Invoked before ApplicationContextAware's setApplicationContext.
     *
     * @param applicationEventPublisher event publisher to be used by this object
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher=applicationEventPublisher;
    }

    public void sendMsg(String msg){
        TestEvent event = new TestEvent(this,"消息体");
        publisher.publishEvent(event);
    }
}
