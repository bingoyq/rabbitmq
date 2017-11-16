package com.yuanq.rabbit.common.service;

import com.yuanq.rabbit.common.ExchangeEnum;
import com.yuanq.rabbit.rabbitmq.Basics;
import org.springframework.amqp.core.Queue;

/**
 * Created by yuanqiang on 2017/11/3.
 */
public abstract class AbstractAdminFactory extends Basics{

    public abstract void createExchange(ExchangeEnum e , String exchange);

    public abstract void binding(ExchangeEnum e , String exchange , String routingKey , String ...queueNames);

    public abstract void binding(ExchangeEnum e ,String exchange ,String routingKey ,Queue...queues);

}
