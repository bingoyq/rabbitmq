package com.yuanq.rabbit.common.realization;

import com.yuanq.rabbit.common.service.AbstractBasicsRabbitMQ;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

/**
 * Created by yuanqiang on 2017/11/3.
 */
public class DirectExchangeRealization extends AbstractBasicsRabbitMQ {

    private String exchange;

    public DirectExchangeRealization(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public void createExchange() {
        this.getRabbitAdmin().declareExchange(new DirectExchange(this.exchange));
    }

    @Override
    public void binding() {

    }

    @Override
    public void binding(Queue... queues) {

    }


}
