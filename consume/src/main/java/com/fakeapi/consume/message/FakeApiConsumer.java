package com.fakeapi.consume.message;

import com.fakeapi.consume.entity.ProductConsum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FakeApiConsumer {

    @RabbitListener(queues = "${broker.queue.api}")
    public void listener(Message message, ProductConsum productEntity){
        log.info("Mensagem recebida {}", productEntity);
    }
}
