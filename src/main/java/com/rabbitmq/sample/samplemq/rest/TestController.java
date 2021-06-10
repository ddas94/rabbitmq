package com.rabbitmq.sample.samplemq.rest;

import com.rabbitmq.sample.samplemq.port.ReceiveFromQueue;
import com.rabbitmq.sample.samplemq.port.SendToQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/queues")
@Slf4j
public class TestController {

    private final ReceiveFromQueue receiveFromQueue;
    private final SendToQueue sendToQueue;

    public TestController(@Qualifier("getReceiveFromQueue") ReceiveFromQueue receiveFromQueue,
                          @Qualifier("getSendToQueue") SendToQueue sendToQueue) {
        this.receiveFromQueue = receiveFromQueue;
        this.sendToQueue = sendToQueue;
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getMessageFromQueue() {
        String message = receiveFromQueue.receiveFromQueue();
        return ResponseEntity.ok(message);
    }

    @PostMapping(value = "/{message}")
    public ResponseEntity<Void> setMessageToQueue(@PathVariable String message) {
        sendToQueue.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
