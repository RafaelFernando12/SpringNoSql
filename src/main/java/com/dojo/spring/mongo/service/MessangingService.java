package com.dojo.spring.mongo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessangingService {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    public void sendMessage(String queueName, Object message) {
        rabbitTemplate.convertAndSend(message);
    }

}
