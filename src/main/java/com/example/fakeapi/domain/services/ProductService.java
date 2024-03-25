package com.example.fakeapi.domain.services;

import java.util.List;
import java.util.Objects;

import com.example.fakeapi.infrastructure.exceptions.ConflictException;
import com.example.fakeapi.infrastructure.exceptions.UnprocessableEntityException;
import com.example.fakeapi.infrastructure.message.producer.FakeApiProducer;
import org.springframework.stereotype.Service;

import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.domain.entities.Product;
import com.example.fakeapi.domain.repositories.ProductRepository;
import com.example.fakeapi.infrastructure.exceptions.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductsMapper mapper;
    private final FakeApiProducer fakeApiProducer;

    public Boolean existsByTitle(String title) {
        try {
            return productRepository.existsByTitle(title);
        }catch (Exception e) {
            throw new BusinessException("Erro ao buscar produto por titulo");
        }
    }

    public ProductsDTO saveProductsInBase(ProductsDTO dto) {
        try {

            var exists = existsByTitle(dto.title());

            if (exists.equals(true)) {
                throw new ConflictException("Produto ja existe");
            }

            return mapper.toDto(productRepository.save(mapper.toEntity(dto)));

        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage());

        } catch (Exception e) {
            throw new BusinessException("Erro: salvar produtos: " + e);
        }
    }

    public void saveProductsConsumer(ProductsDTO dto) {
        try {

            var exists = existsByTitle(dto.title());

            if (exists.equals(true)){
                fakeApiProducer.sendResponseProducts("Produto" +dto.title() +" ja existe");
                throw new ConflictException("Produto ja existe");
            }

            productRepository.save(mapper.toEntity(dto));

            fakeApiProducer.sendResponseProducts("Produto "+ dto.title() +" sucesso");

        }catch (ConflictException e){
            throw new ConflictException(e.getMessage());

        } catch (Exception e) {
            fakeApiProducer.sendResponseProducts("Erro ao cadastrar produto "+ dto.title());
            throw new BusinessException("Erro: salvar produtos: " + e);
        }
    }

    public List<ProductsDTO> searchAllProducts() {
        try {
            return mapper.toListDto(productRepository.findAll());
        } catch (Exception e) {
            throw new BusinessException("Erro: buscar todo os produtos: " + e);
        }
    }

    public ProductsDTO searchProductByTitle(String title) {
        try {
            var entity = productRepository.findByTitle(title);

            if (Objects.isNull(entity)) {
                throw new UnprocessableEntityException("Não foram encontrados produtos com esse nome");
            }

            return mapper.toDto(entity);

        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());

        } catch (Exception e) {
            throw new BusinessException("Erro: buscar produtos por titulo" + e);
        }
    }

    public void deleteProductById(String id) {
        try {
            var entity = existsId(id);

            if (entity.equals(false)) {
                throw new UnprocessableEntityException("Produto não existe");
            } else {
                productRepository.deleteById(id);
            }

        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());

        } catch (Exception e) {
            throw new BusinessException("Erro: deletar por id " + e);
        }
    }

    public ProductsDTO updateProduct(String id, ProductsDTO dto) {

        try {
            Product entity = productRepository.findById(id)
                    .orElseThrow(() -> new UnprocessableEntityException("Produto não existe"));

            return mapper.toDto(productRepository.save(mapper.toEntityUpdate(entity, dto)));

        } catch (UnprocessableEntityException e) {
            throw new UnprocessableEntityException(e.getMessage());

        } catch (Exception e) {
            throw new BusinessException("Erro: ao atualizar produto " + e);
        }

    }

    public Boolean existsId(String id) {
        return productRepository.existsById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }
}
