package com.yuanq.rabbit.handler;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public abstract class ManualConsumerHandler implements ChannelAwareMessageListener {

    private static Logger logger = LoggerFactory.getLogger(ManualConsumerHandler.class);

    public void onMessage(Message message, Channel channel) throws Exception {
        logger.debug("监听消息：".concat(message.toString()));

        if(handlerMessage(message))
            channel.basicAck(message.getMessageProperties().getDeliveryTag() , false);
    }

    protected abstract boolean handlerMessage (Message message);

}
