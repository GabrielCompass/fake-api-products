package com.example.fakeapi.infrastructure.message.consumer;


import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.domain.services.ProductService;
import com.example.fakeapi.infrastructure.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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


    @KafkaListener(topics = "${topic.fake.api.consumer.name}", groupId = "${topic.fake.api.consumer.group.id}")
    public void consumerProducerProducts(ProductsDTO productsDTO){
        try {
            productService.saveProductsConsumer(productsDTO);
        }catch (Exception e){
            throw new BusinessException("Erro: consumir mensagem do kafka "+ e);
        }

}
