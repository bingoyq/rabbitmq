package com.yuanq.rabbit.rabbitmq;

import com.yuanq.rabbit.listener.ConsumerMessageListenerContainer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public class Consumer extends Basics {

    private String[] queueNames ;
    private int consumerCount = 1;
    private ChannelAwareMessageListener channelAwareMessageListener;

    public Consumer (String host , int port ,String username ,String password , int consumerCount ,String ...queueNames) {
        this.setHost(host);
        this.setPassword(password);
        this.setPort(port);
        this.setUsername(username);
        this.queueNames = queueNames;
        this.consumerCount = consumerCount;
        super.init();
    }

    public void run (){
        ConsumerMessageListenerContainer container = new ConsumerMessageListenerContainer();
        container.setConnectionFactory(this.getConnectionFactory());
        container.setQueueNames(this.queueNames);
        container.setConcurrentConsumers(this.consumerCount);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(this.channelAwareMessageListener);
        container.start();
    }

    public void setChannelAwareMessageListener(ChannelAwareMessageListener channelAwareMessageListener) {
        this.channelAwareMessageListener = channelAwareMessageListener;
    }
}
