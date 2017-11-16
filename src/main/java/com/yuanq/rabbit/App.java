package com.yuanq.rabbit;

import com.yuanq.rabbit.common.AdminRealization;
import com.yuanq.rabbit.common.ExchangeEnum;
import com.yuanq.rabbit.handler.AutoConsumerHandler;
import com.yuanq.rabbit.rabbitmq.Consumer;
import com.yuanq.rabbit.rabbitmq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public class App {

    private static String exchange = "rabbit.fanout.exchange.test";
    private static String message = "hello word ...";

    private static String host = "192.168.3.201";
    private static int port = 5672 ;
    private static String username = "guest";
    private static String password = "guest";

    private static int consumerCount = 1;
    private static String [] queues = new String[] {"rabbit.fanout.queue.test.01"};

    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        production();

        consumption();
    }

    /**
     * 生产
     */
    public static void production() {

        Producer producer = new Producer(host, port, username, password);

        //producer.createRule(ExchangeEnum.FANOUT , exchange);

        AdminRealization realization = AdminRealization.getInstance();
        realization.setRabbitAdmin(producer.getRabbitAdmin());

        realization.createExchange(ExchangeEnum.FANOUT ,exchange);

        for (int i = 0; i < 20; i++) {
            producer.send(exchange , null , message);
        }

    }

    /**
     * 消费
     */
    public static void consumption() {


        Consumer consumer = new Consumer(host ,port ,username ,password ,consumerCount , queues);

        AdminRealization realization = AdminRealization.getInstance();
        realization.setRabbitAdmin(consumer.getRabbitAdmin());

        realization.binding(ExchangeEnum.FANOUT ,exchange ,null ,queues); // 绑定关系

//        consumer.setChannelAwareMessageListener((m , c) -> {
//            logger.debug(m.toString());
//            c.basicAck(m.getMessageProperties().getDeliveryTag() ,false);
//        });

        consumer.setChannelAwareMessageListener(new AutoConsumerHandler() {
            @Override
            protected void handlerMessage(Message message) {

            }
        });
        consumer.run();
    }

}
