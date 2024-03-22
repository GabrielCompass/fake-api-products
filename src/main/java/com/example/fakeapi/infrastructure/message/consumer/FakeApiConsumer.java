package com.example.fakeapi.infrastructure.message.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.domain.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Component
public class FakeApiConsumer {

    private final ProductService productService;

    //@KafkaListener(topics = "${topic.fake.api.consumer.name}", groupId = "${topic.fake.api.consumer.group.id}")
    public void consumerProducerProducts(ProductsDTO productsDTO) {
        productService.saveProductsInBase(productsDTO);
    }
}
