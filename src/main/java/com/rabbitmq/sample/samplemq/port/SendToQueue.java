package com.rabbitmq.sample.samplemq.port;

public interface SendToQueue {

    void sendMessage(String message);
}
