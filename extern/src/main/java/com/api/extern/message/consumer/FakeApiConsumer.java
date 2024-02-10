package com.api.extern.message.consumer;

import com.api.extern.service.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class FakeApiConsumer {
    @Value(value = "${broker.queue.fake-api.consumer}")
    public String queue;

    @RabbitListener(queues = queue)
    public void listener(Message message, ProductEntity productEntity){

    }

}
