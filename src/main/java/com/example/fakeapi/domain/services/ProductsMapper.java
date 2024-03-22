package com.example.fakeapi.domain.services;

import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.domain.entities.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ProductsMapper {

    public Product toEntity(ProductsDTO dto) {
        return new Product(
                dto.id(),
                dto.title(),
                dto.price(),
                dto.category(),
                dto.description(),
                dto.image(),
                LocalDateTime.now());
    }

    public ProductsDTO toDto(Product entity) {
        return new ProductsDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getPrice(),
                entity.getCategory(),
                entity.getDescription(),
                entity.getImage(),
                entity.getCreatedAt(),
                entity.getUpdateAt());
    }

    public Product toEntityUpdate(Product entity, ProductsDTO dto) {
        String title = (dto.title() != null) ? dto.title() : entity.getTitle();

        BigDecimal price = (dto.price() != null) ? dto.price() : entity.getPrice();

        String category = (dto.category() != null) ? dto.category() : entity.getCategory();

        String description = (dto.description() != null) ? dto.description() : entity.getDescription();

        String image = (dto.image() != null) ? dto.image() : entity.getImage();

        return new Product(
                entity.getId(),
                title,
                price,
                category,
                description,
                image,
                entity.getCreatedAt(),
                LocalDateTime.now());
    }

    public List<ProductsDTO> toListDto(List<Product> entityList) {
        return entityList.stream().map(this::toDto).toList();
    }
}
