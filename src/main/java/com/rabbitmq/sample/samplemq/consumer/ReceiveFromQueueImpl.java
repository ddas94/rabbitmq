package com.rabbitmq.sample.samplemq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.sample.samplemq.connection.QueueConnectionFactory;
import com.rabbitmq.sample.samplemq.exceptions.ReceiveFromQueueException;
import com.rabbitmq.sample.samplemq.port.ReceiveFromQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static com.rabbitmq.sample.samplemq.constants.ApiConstants.QUEUE_NAME;

@Component
@Slf4j
public class ReceiveFromQueueImpl implements ReceiveFromQueue {

    private final QueueConnectionFactory connectionFactory;

    public ReceiveFromQueueImpl(QueueConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public String receiveFromQueue() {
        AtomicReference<String> payload = new AtomicReference<>();
        Channel channel
                = connectionFactory.getChannel();
        try {
            DeliverCallback deliverCallback
                    = ((consumerTag, message) -> {
                String receivedMessage = new String(message.getBody(), StandardCharsets.UTF_8);
                log.info(receivedMessage);
                payload.set(receivedMessage);
            });
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            throw new ReceiveFromQueueException("Error while receiving message from queue", e);
        }
        return payload.get();
    }
}
