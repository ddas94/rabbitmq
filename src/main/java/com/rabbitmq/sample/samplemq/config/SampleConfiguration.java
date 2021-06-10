package com.rabbitmq.sample.samplemq.config;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.sample.samplemq.connection.QueueConnectionFactory;
import com.rabbitmq.sample.samplemq.consumer.ReceiveFromQueueImpl;
import com.rabbitmq.sample.samplemq.port.ReceiveFromQueue;
import com.rabbitmq.sample.samplemq.port.SendToQueue;
import com.rabbitmq.sample.samplemq.producer.SendToQueueImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfiguration {

    @Bean
    public ConnectionFactory getConnectionFactory() {
        return new ConnectionFactory();
    }

    @Bean
    public ReceiveFromQueue getReceiveFromQueue(QueueConnectionFactory connectionFactory) {
        return new ReceiveFromQueueImpl(connectionFactory);
    }

    @Bean
    public SendToQueue getSendToQueue(QueueConnectionFactory connectionFactory) {
        return new SendToQueueImpl(connectionFactory);
    }
}
