package com.api.extern.message.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class RabbitMQConConfig {

    private final DirectExchange directExchange;

    public static final String ROUTING_KEY_NAME = "broker.queue.fake-api.producer";
    private static final String QUEUE_NAME = "broker.queue.fale-api.consumer";

    @Bean
    Queue queue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange).with(ROUTING_KEY_NAME);
    }


}
