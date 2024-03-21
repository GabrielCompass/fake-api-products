package com.example.fakeapi.api.controller;

import com.example.fakeapi.api.dto.ProductsDTO;
import com.example.fakeapi.domain.services.FakeApiService;
import com.example.fakeapi.domain.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {

    private final FakeApiService fakeApiService;
    private final ProductService productService;

    @DeleteMapping
    public void deleteAll(){
        productService.deleteAll();
    }
    @PostMapping("/api")
    public ResponseEntity<List<ProductsDTO>> salvaProdutosApi(){
        return new ResponseEntity<>(fakeApiService.searchProductsInApi(),HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductsDTO>> searchAllProducts(){
        return new ResponseEntity<>(productService.searchAllProducts(), HttpStatus.FOUND);
    }

    @GetMapping("/{title}")
    public ResponseEntity<ProductsDTO> searchProductByTitle(@RequestParam String title){
        return new ResponseEntity<>(productService.searchProductByTitle(title), HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@RequestParam String id){
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProduct (@RequestParam String id, @RequestBody ProductsDTO dto){
        return new ResponseEntity<>(productService.updateProduct(id, dto), HttpStatus.ACCEPTED);
    }
}
