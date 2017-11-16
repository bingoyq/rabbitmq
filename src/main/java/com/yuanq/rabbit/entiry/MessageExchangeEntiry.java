package com.yuanq.rabbit.entiry;

import org.springframework.amqp.rabbit.core.RabbitAdmin;

/**
 * Created by yuanqiang on 2017/11/3.
 */
public class MessageExchangeEntiry {

    private String exchange ;
    private String [] queueNames ;
    private String routingKey;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String[] getQueueNames() {
        return queueNames;
    }

    public void setQueueNames(String[] queueNames) {
        this.queueNames = queueNames;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

}
