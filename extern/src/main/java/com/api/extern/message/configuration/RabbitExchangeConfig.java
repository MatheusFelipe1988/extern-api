package com.api.extern.message.configuration;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitExchangeConfig {
    public static final String EXCHANGE_NAME = "broker.queue.fake-api.producer";

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

}
