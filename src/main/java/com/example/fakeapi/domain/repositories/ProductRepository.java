package com.example.fakeapi.domain.repositories;

import com.example.fakeapi.domain.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Product findByTitle(String title);

    Boolean existsByTitle(String title);

}
