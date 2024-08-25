package com.priyanshudev.productcatalog.controllers;

import com.priyanshudev.productcatalog.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @GetMapping("/products")
    public String getAllProducts() {
        return "All Products";
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable Long id) {
        System.out.println("Id is " + id);
        return "Product Details of " + id;
    }

    public String getProductsInCategory(Long id) {
        System.out.println("Id is " + id);
        return "Product Details of category id : " + id;
    }

    @PostMapping("/products")
    public String addNewProduct(@RequestBody ProductDto productDto) {
        return "New Product added..."+productDto;
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "Updated Product Details of id : " + id;
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Deleted Product Details of id : " + id;
    }
}
