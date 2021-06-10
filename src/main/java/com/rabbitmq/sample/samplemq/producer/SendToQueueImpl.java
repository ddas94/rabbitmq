package com.rabbitmq.sample.samplemq.producer;

import static com.rabbitmq.sample.samplemq.constants.ApiConstants.QUEUE_NAME;

import com.rabbitmq.client.Channel;
import com.rabbitmq.sample.samplemq.connection.QueueConnectionFactory;
import com.rabbitmq.sample.samplemq.exceptions.SendToQueueException;
import com.rabbitmq.sample.samplemq.port.SendToQueue;
import org.springframework.stereotype.Component;

@Component
public class SendToQueueImpl implements SendToQueue {

    private final QueueConnectionFactory connectionFactory;

    public SendToQueueImpl(QueueConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void sendMessage(String message) {
        Channel channel
                = connectionFactory.getChannel();
        try {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (Exception e) {
            throw new SendToQueueException("Exception while publishing message to queue", e);
        }
    }
}
