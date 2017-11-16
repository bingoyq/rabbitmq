package com.yuanq.rabbit.rabbitmq;

import com.yuanq.rabbit.common.AdminRealization;
import com.yuanq.rabbit.common.ExchangeEnum;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public class Producer extends Basics {

    public Producer (String host , int port ,String username ,String password) {
        this.setHost(host);
        this.setPassword(password);
        this.setPort(port);
        this.setUsername(username);
        super.init();
    }

    public void createRule(ExchangeEnum e , String exchange) {
        AdminRealization.getInstance().createExchange(e , exchange);
    }

    public void send (Object message) {
        this.getRabbitTemplate().convertAndSend(message);
    }

    public void send (String exchange ,String routingKey , Object message) {
        this.getRabbitTemplate().convertAndSend(exchange ,routingKey ,message);
    }

}
