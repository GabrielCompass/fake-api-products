package com.example.fakeapi.domain.services;

import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.domain.entities.Product;
import com.example.fakeapi.domain.repositories.ProductRepository;
import com.sun.source.tree.TryTree;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductsMapper mapper;

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public ProductsDTO saveProductsInBase(ProductsDTO dto) {
        try {
            var entity = mapper.toEntity(dto);
            var response = productRepository.save(entity);
            return mapper.toDto(response);
        } catch (Exception e) {
            throw new RuntimeException("Error in saveProductsInBase: " + e);
        }
    }

    public List<ProductsDTO> searchAllProducts() {
        try {
            return mapper.toListDto(productRepository.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Error in searchAllProducts: " + e);
        }
    }

    public ProductsDTO searchProductByTitle(String title) {
        try {
            return mapper.toDto(productRepository.findByTitle(title));
        } catch (Exception e) {
            throw new RuntimeException("Error in searchProductByTitle: " + e);
        }
    }

    public void deleteProductById(String id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in deleteProductById: " + e);
        }
    }

    public ProductsDTO updateProduct(String id, ProductsDTO dto) {

        try {
            Product entity = productRepository.findById(id).orElseThrow( () -> new RuntimeException("Error in updateProduct"));
            System.out.println(entity.getTitle());
            return mapper.toDto( productRepository.save( mapper.toEntityUpdate(entity, dto)));
        } catch (Exception e) {
            throw new RuntimeException("Error in updateProduct: " + e);
        }

    }

    public Boolean existsId(String id){
       return productRepository.existsById(id);
    }
}
