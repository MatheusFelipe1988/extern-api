package com.api.extern.message.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class RabbitMQConConfig {

    private final DirectExchange directExchange;

    public static final String ROUTING_KEY_NAME = "broker.queue.fake-api.producer";
    private static final String QUEUE_NAME = "broker.queue.fake-api";

    @Bean
    Queue queue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange).with(ROUTING_KEY_NAME);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(mapper);
    }


}
