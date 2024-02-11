package com.api.extern.message.producer;

import com.api.extern.service.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.api.extern.message.configuration.RabbitExchangeConfig.EXCHANGE_NAME;
import static com.api.extern.message.configuration.RabbitMQConConfig.ROUTING_KEY_NAME;

@RequiredArgsConstructor
@Component
public class FakeApiProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(ProductEntity productEntity){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME);
    }

}
