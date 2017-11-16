package com.yuanq.rabbit.entiry;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuanqiang on 2017/11/6.
 */
public class QueueParameterEntity {

    private int xMaxLength = -1; // x-max-length 队列中最大消息量，不能为负数。当消息大于设置，后面当消息将覆盖前面消息
    private int xMaxLengthBytes = -1; // x-max-length-bytes 队列中最大消息容量，不能为负数。
    private int xMessageTtl = -1; // x-message-ttl 队列中消息存活时间，不能为负数。时间单位（秒）
    private int xExpires = -1; // x-expires 消息队列本身存活时间，不能为负数 。时间单位（秒）
    private int xMaxPriority = -1; // x-max-priority 消息优先级，建议 1- 10 。

    private String xDeadLetterRoutingKey; // x-dead-letter-routing-key ，配合消息存活时间属性。当消息超时后，可放入指定队列。可以是队列名称／或队列路由key
    private String xDeadLetterExchange; // x-dead-letter-exchange ，配合消息存活时间属性。当消息超时后，可以放入指定规则中。

    private Map<String ,Object > arguments = null;

    /**
     * 队列扩展属性，消息存活时间
     * @param xMessageTtl
     */
    public QueueParameterEntity(int xMessageTtl) {
        this.xMessageTtl = xMessageTtl;

        toMap();
    }

    /**
     * 队列扩展参数，队列存储量／消息存活时间
     * @param xMaxLength
     * @param xMessageTtl
     */
    public QueueParameterEntity(int xMaxLength , int xMessageTtl) {
        this.xMaxLength = xMaxLength ;
        this.xMessageTtl = xMessageTtl ;

        toMap();
    }

    /**
     * 队列扩展参数全实现 ；参数值为 0 时，创建队列不增加扩展属性。（xmessagettl/xdead..key/xdead..ex ；三个属性配合使用，若无消息存活值为 0 ，后两个扩展属性不实现）
     * @param xMaxLength
     * @param xMaxLengthBytes
     * @param xMessageTtl
     * @param xExpires
     * @param xMaxPriority
     * @param xDeadLetterRoutingKey
     * @param xDeadLetterExchange
     */
    public QueueParameterEntity(int xMaxLength , int xMaxLengthBytes , int xMessageTtl , int xExpires , int xMaxPriority , String xDeadLetterRoutingKey , String xDeadLetterExchange) {
        this.xMaxLength = xMaxLength ;
        this.xMaxLengthBytes = xMaxLengthBytes ;
        this.xMessageTtl = xMessageTtl ;
        this.xExpires = xExpires ;
        this.xMaxPriority = xMaxPriority ;
        this.xDeadLetterExchange = xDeadLetterExchange ;
        this.xDeadLetterRoutingKey = xDeadLetterRoutingKey ;

        toMap();
    }

    /**
     * 将对象转换为实际创建map对象，保证没穿参数不下发
     */
    private void toMap () {
        this.arguments = new HashMap<String ,Object>();

        if(this.xMaxLength > 0)
            arguments.put("x-max-length" , this.xMaxLength );
        if(this.xMaxLengthBytes > 0)
            arguments.put("x-max-length-bytes" , this.xMaxLengthBytes );
        if(this.xMessageTtl > 0)
            arguments.put("x-message-ttl" , this.xMessageTtl * 1000 );
        if(this.xExpires > 0)
            arguments.put("x-expires" , this.xExpires * 1000 );
        if(this.xMaxPriority > 0)
            arguments.put("x-max-priority" , this.xMaxPriority );
        if(!StringUtils.isEmpty(this.xDeadLetterExchange))
            arguments.put("x-dead-letter-exchange" , this.xDeadLetterExchange );
        if(!StringUtils.isEmpty(this.xDeadLetterRoutingKey))
            arguments.put("x-dead-letter-routing-key" , this.xDeadLetterRoutingKey );
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }
}
