package com.rabbitmq.sample.samplemq.exceptions;

public class SendToQueueException extends RuntimeException {

    public SendToQueueException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
