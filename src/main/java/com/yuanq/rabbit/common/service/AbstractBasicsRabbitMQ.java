package com.yuanq.rabbit.common.service;

import com.yuanq.rabbit.rabbitmq.Basics;
import org.springframework.amqp.core.Queue;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public abstract class AbstractBasicsRabbitMQ extends Basics{

    public abstract void createExchange();

    public abstract void binding();

    public abstract void binding(Queue ...queues);

}
