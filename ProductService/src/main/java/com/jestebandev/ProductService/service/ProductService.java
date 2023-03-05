package com.jestebandev.ProductService.service;

import com.jestebandev.ProductService.dto.ProductRequest;
import com.jestebandev.ProductService.dto.ProductResponse;
import com.jestebandev.ProductService.model.Product;
import com.jestebandev.ProductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }

    @Override
    public List<ProductResponse> listProduct() {
    List<Product> listProducts=productRepository.findAll();
    return listProducts.stream().map(this::mapToProductResponse).toList();
    }
    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }
}
