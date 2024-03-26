package com.example.fakeapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductsDTO(

                String id,

                @NotNull @NotBlank
                String title,
                BigDecimal price,
                String category,
                String description,
                String image,
                LocalDateTime createdAt,
                LocalDateTime updateAt) {
}
