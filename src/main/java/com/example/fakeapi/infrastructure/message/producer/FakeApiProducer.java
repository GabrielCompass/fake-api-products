package com.example.fakeapi.infrastructure.message.producer;

import com.example.fakeapi.infrastructure.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FakeApiProducer {

    @Value("${topic.fake.api.consumer.name}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendResponseProducts(final String message){
        try {
            kafkaTemplate.send(topic, message);
        }catch (BusinessException e){
            throw new BusinessException("Erro ao produzir mensagem do kafka"+e.getMessage());
        }
    }

}
