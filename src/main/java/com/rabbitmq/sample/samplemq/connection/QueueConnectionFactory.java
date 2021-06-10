package com.rabbitmq.sample.samplemq.connection;

import static com.rabbitmq.sample.samplemq.constants.ApiConstants.QUEUE_NAME;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueConnectionFactory {

    private final ConnectionFactory connectionFactory;

    public QueueConnectionFactory(@Qualifier("getConnectionFactory") ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Channel getChannel() {
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672); // default port for local applications
        connectionFactory.setVirtualHost("/");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            return channel;
        } catch (Exception exception) {
            log.error("Exception while creating connection", exception.getStackTrace().toString());
            throw new RuntimeException("Exception while creating connection", exception.getCause());
        }
    }
}
