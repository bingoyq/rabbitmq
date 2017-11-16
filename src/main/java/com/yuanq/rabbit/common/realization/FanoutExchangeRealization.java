package com.yuanq.rabbit.common.realization;

import com.yuanq.rabbit.common.service.AbstractBasicsRabbitMQ;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;

import java.util.Arrays;

/**
 * Created by yuanqiang on 2017/11/2.
 */
public class FanoutExchangeRealization extends AbstractBasicsRabbitMQ {

    private String exchange ;
    private String[] queues ;
    private String routingKey ;

    public FanoutExchangeRealization(String exchange ,String routingKey ,String ...queues) {
        this.exchange = exchange;
        this.queues = queues;
        this.routingKey = routingKey;
    }

    @Override
    public void createExchange() {
        super.getRabbitAdmin().declareExchange(new FanoutExchange(this.exchange));
    }

    @Override
    public void binding() {
        if(this.queues.length > 0)
            Arrays.asList(queues).forEach((q) -> super.getRabbitAdmin().declareBinding(BindingBuilder.bind(createQueue(q)).to(new FanoutExchange(this.exchange))));
    }

    @Override
    public void binding(Queue... queues) {
        if(this.queues.length > 0)
            Arrays.asList(queues).forEach((q) -> super.getRabbitAdmin().declareBinding(BindingBuilder.bind(q).to(new FanoutExchange(this.exchange))));
    }

    private Queue createQueue(String name) {
        Queue queue = new Queue(name);
        this.getRabbitAdmin().declareQueue(queue);
        return queue;
    }

}
