package com.example.fakeapi.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String title;
    private BigDecimal price;
    private String category;
    private String description;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


    public Product(String id, String title, BigDecimal price, String category, String description, String image, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
    }
}
