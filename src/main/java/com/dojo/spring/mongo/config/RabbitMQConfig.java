package com.dojo.spring.mongo.config;



import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue personNotificaQueue() {
        return new Queue("person_notifications",true);
    }
}
