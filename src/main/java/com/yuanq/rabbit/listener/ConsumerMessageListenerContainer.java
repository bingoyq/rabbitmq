package com.yuanq.rabbit.listener;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public class ConsumerMessageListenerContainer extends SimpleMessageListenerContainer{

    public void doStart() throws Exception {
        super.doStart();
    }

}
