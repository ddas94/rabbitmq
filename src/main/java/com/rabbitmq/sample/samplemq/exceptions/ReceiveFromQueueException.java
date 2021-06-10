package com.rabbitmq.sample.samplemq.exceptions;

public class ReceiveFromQueueException extends RuntimeException {

    public ReceiveFromQueueException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
