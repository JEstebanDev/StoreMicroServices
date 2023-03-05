package com.jestebandev.ProductService.controller;

import com.jestebandev.ProductService.dto.ProductRequest;
import com.jestebandev.ProductService.dto.Response;
import com.jestebandev.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createProduct(@RequestBody ProductRequest productRequest) {
        return new Response(Instant.now(),HttpStatus.CREATED.value(),HttpStatus.CREATED,
                        "Created product",Map.of("product", productService.createProduct(productRequest)));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAllProducts() {
        return new Response(Instant.now(),HttpStatus.OK.value(),HttpStatus.OK,
                "List products",Map.of("products", productService.listProduct()));
    }

}
