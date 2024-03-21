package com.example.fakeapi.domain.services;

import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.infrastructure.FakeApiClient;
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
                    }

            );
            return productService.searchAllProducts();
        } catch (Exception e) {
            throw new RuntimeException("Error searchProductsInApi: "+e);
        }
    }
}
