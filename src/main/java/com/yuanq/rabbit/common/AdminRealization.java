package com.yuanq.rabbit.common;

import com.yuanq.rabbit.common.realization.DirectExchangeRealization;
import com.yuanq.rabbit.common.realization.FanoutExchangeRealization;
import com.yuanq.rabbit.common.realization.TopicExchangeRealization;
import com.yuanq.rabbit.common.service.AbstractAdminFactory;
import com.yuanq.rabbit.common.service.AbstractBasicsRabbitMQ;
import com.yuanq.rabbit.entiry.QueueParameterEntity;
import org.springframework.amqp.core.Queue;
import org.springframework.util.StringUtils;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public class AdminRealization extends AbstractAdminFactory {

    private static AdminRealization realization ;

    private AdminRealization(){}

    public static AdminRealization getInstance() {
        if(realization == null)
            realization = new AdminRealization();
        return realization;
    }

    @Override
    public void createExchange(ExchangeEnum e, String exchange) {
        if(StringUtils.isEmpty(exchange)) throw new NullPointerException("exchange parameter , Can not be empty.");
        switch (e) {
            case FANOUT:
                AbstractBasicsRabbitMQ fb = new FanoutExchangeRealization(exchange , null ,null) ;
                fb.setRabbitAdmin(this.getRabbitAdmin());
                fb.createExchange();
                break;
            case DIRECT:
                AbstractBasicsRabbitMQ db = new DirectExchangeRealization(exchange);
                db.setRabbitAdmin(this.getRabbitAdmin());
                db.createExchange();
                break;
            case TOPIC:
                AbstractBasicsRabbitMQ tb = new TopicExchangeRealization(exchange , null ,null);
                tb.setRabbitAdmin(this.getRabbitAdmin());
                tb.createExchange();
                break;
        }
    }

    @Override
    public void binding(ExchangeEnum e, String exchange, String routingKey, String... queueNames) {
        if(StringUtils.isEmpty(exchange) || queueNames.length <= 0) throw new NullPointerException("exchange parameter or queueNames parameter , Can not be empty.");
        switch (e) {
            case FANOUT:
                AbstractBasicsRabbitMQ fb = new FanoutExchangeRealization(exchange , routingKey ,queueNames) ;
                fb.setRabbitAdmin(this.getRabbitAdmin());
                fb.binding();
                break;
            case TOPIC:
                AbstractBasicsRabbitMQ tb = new TopicExchangeRealization(exchange , routingKey ,queueNames);
                tb.setRabbitAdmin(this.getRabbitAdmin());
                tb.binding();
                break;
        }
    }

    @Override
    public void binding(ExchangeEnum e, String exchange, String routingKey, Queue... queues) {
        if(StringUtils.isEmpty(exchange) || queues.length <= 0) throw new NullPointerException("exchange parameter or queueNames parameter , Can not be empty.");
        switch (e) {
            case FANOUT:
                AbstractBasicsRabbitMQ fb = new FanoutExchangeRealization(exchange , routingKey ,null) ;
                fb.setRabbitAdmin(this.getRabbitAdmin());
                fb.binding(queues);
                break;
            case TOPIC:
                AbstractBasicsRabbitMQ tb = new TopicExchangeRealization(exchange , routingKey ,null);
                tb.setRabbitAdmin(this.getRabbitAdmin());
                tb.binding(queues);
                break;
        }
    }

    /**
     * 自定义
     * @param queue
     * @param entity
     * @return
     */
    public Queue createQueue(String queue , QueueParameterEntity entity){
        Queue svr = null ;

        if(entity != null && entity.getArguments().size() > 0)
            svr = new Queue(queue , true ,false , false , entity.getArguments());
        else
            svr = new Queue(queue ,true );

        this.getRabbitAdmin().declareQueue(svr);
        return svr;
    }

    /**
     * 默认
     * @param name
     * @return
     */
    private Queue createQueue(String name) {
        Queue queue = new Queue(name);
        this.getRabbitAdmin().declareQueue(queue);
        return queue;
    }

}
