package com.api.extern.message.consumer;

import com.api.extern.service.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class FakeApiConsumer {
    public static final String QUEUE_NAME = "broker.queue.fake-api";

    @RabbitListener(queues = QUEUE_NAME)
    public void listener(Message message, ProductEntity productEntity){
        log.info("Mensagem recebida {}", productEntity);
    }

}
