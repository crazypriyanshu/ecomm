package com.priyanshudev.productcatalog.proxyServer.controllers;

import com.priyanshudev.productcatalog.proxyServer.dtos.GetSingleProductResponseDto;
import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import com.priyanshudev.productcatalog.proxyServer.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String root(){
        return "Hello From home page";

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("con", "application");
        headers.add("TC", "24");
        ResponseEntity<Product> responseEntity = new ResponseEntity(productService.getSingleProduct(id), headers, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/product")
    public ResponseEntity<ProxyCreateProductDto> addProduct(@RequestBody ProxyCreateProductDto product) {
        ProxyCreateProductDto newProduct = productService.addNewProduct(product);

        ResponseEntity<Product> response = new ResponseEntity(newProduct, HttpStatus.CREATED);
        return new ResponseEntity(newProduct, HttpStatus.CREATED);

    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
        return responseEntity;

    }

}
