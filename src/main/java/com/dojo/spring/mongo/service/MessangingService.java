package com.dojo.spring.mongo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessangingService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.routing.key.person}")
    private String personRoutingKey;

    @Value("${rabbitmq.exchange.default}")
    private String defaultExchange;

    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend(defaultExchange, personRoutingKey, message);
    }

}
