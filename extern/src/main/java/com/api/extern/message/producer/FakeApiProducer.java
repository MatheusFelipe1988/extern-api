package com.api.extern.message.producer;

import com.api.extern.service.exception.BusinessException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FakeApiProducer {
    final RabbitTemplate rabbitTemplate;

    public FakeApiProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value(value = "${broker.queue.fake-api.producer}")
    private String routingKey;

    public void publishMessage(String mensagem){
        try {
            rabbitTemplate.convertAndSend("", routingKey, mensagem);
        }catch (Exception e){
            throw new BusinessException("Erro to produce message");
        }
    }




}
