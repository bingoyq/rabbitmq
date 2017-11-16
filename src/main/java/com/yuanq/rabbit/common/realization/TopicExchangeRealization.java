package com.yuanq.rabbit.common.realization;

import com.yuanq.rabbit.common.service.AbstractBasicsRabbitMQ;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import java.util.Arrays;

/**
 * Created by yuanqiang on 2017/11/3.
 */
public class TopicExchangeRealization extends AbstractBasicsRabbitMQ {

    private String exchange;
    private String routingKey;
    private String[] queues;

    public TopicExchangeRealization(String exchange , String routingKey , String ...queues) {
        this.exchange = exchange ;
        this.routingKey = routingKey ;
        this.queues = queues;
    }

    @Override
    public void createExchange() {
        this.getRabbitAdmin().declareExchange(new TopicExchange(this.exchange));
    }

    @Override
    public void binding() {
        if(this.queues.length > 0)
            Arrays.asList(this.queues).forEach((q) -> this.getRabbitAdmin().declareBinding(BindingBuilder.bind(createQueue(q)).to(new TopicExchange(this.exchange)).with(this.routingKey)));
    }

    @Override
    public void binding(Queue... queues) {
        Arrays.asList(queues).forEach((q) -> this.getRabbitAdmin().declareBinding(BindingBuilder.bind(q).to(new TopicExchange(this.exchange)).with(this.routingKey)));

    }

    private Queue createQueue(String name) {
        Queue queue = new Queue(name);
        this.getRabbitAdmin().declareQueue(queue);
        return queue;
    }

}
