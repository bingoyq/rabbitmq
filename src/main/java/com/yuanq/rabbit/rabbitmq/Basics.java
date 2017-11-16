package com.yuanq.rabbit.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by yuanqiang on 2017/11/16.
 */
public class Basics {

    private String host;
    private String username;
    private String password;
    private int port;

    private ConnectionFactory connectionFactory;
    private CachingConnectionFactory factory;
    private RabbitAdmin rabbitAdmin;
    private RabbitTemplate rabbitTemplate;

    public void init() {
        initFactory();
        initRabbitAdmin();
        initRabbitTemplate();
    }

    private void initFactory () {
        if(connectionFactory == null) {
            try {
                factory = new CachingConnectionFactory();
                factory.setHost(this.host);
                factory.setPort(this.port);
                factory.setUsername(this.username);
                factory.setPassword(this.password);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.setConnectionFactory(factory);
    }

    private void initRabbitAdmin(){
        if(rabbitAdmin == null)
            this.setRabbitAdmin(new RabbitAdmin(this.connectionFactory));
    }

    private void initRabbitTemplate(){
        if(rabbitTemplate == null)
            this.setRabbitTemplate(new RabbitTemplate(this.connectionFactory));
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public RabbitAdmin getRabbitAdmin() {
        return rabbitAdmin;
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }
}
