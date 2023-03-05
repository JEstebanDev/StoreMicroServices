package com.jestebandev.ProductService.service;

import com.jestebandev.ProductService.dto.ProductRequest;
import com.jestebandev.ProductService.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    ProductResponse createProduct(ProductRequest product);
    List<ProductResponse> listProduct();
}
