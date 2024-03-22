package com.example.fakeapi.domain.services;

import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.infrastructure.FakeApiClient;
import com.example.fakeapi.infrastructure.exceptions.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final FakeApiClient fakeApiClient;
    private final ProductService productService;

    public List<ProductsDTO> searchProductsInApi() {
        try {

            List<ProductsDTO> dto = fakeApiClient.searchProductsApi();
            dto.forEach(product -> {
                        Boolean response = productService.existsId(product.id());
                        if (response.equals(false)) {
                            productService.saveProductsInBase(product);
                        }
                        else {
                                throw new ConflictException("Produto ja existe no banco de dados");
                        }
                    }
            );
            return productService.searchAllProducts();

        }catch (ConflictException e){
            throw new ConflictException(e.getMessage());

        } catch (Exception e) {
            throw new RuntimeException("Error searchProductsInApi: "+e);
        }
    }
}
