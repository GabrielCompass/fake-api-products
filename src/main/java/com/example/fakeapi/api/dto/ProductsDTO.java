package com.example.fakeapi.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductsDTO(

                String id,
                String title,
                BigDecimal price,
                String category,
                String description,
                String image,
                LocalDateTime createdAt,
                LocalDateTime updateAt) {
}
