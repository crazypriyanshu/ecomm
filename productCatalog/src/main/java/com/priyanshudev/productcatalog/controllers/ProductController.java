package com.priyanshudev.productcatalog.controllers;

import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.ErrorResponseDto;
import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.ProxyProductDto;
import com.priyanshudev.productcatalog.exceptions.NotFoundException;
import com.priyanshudev.productcatalog.models.Product;
import com.priyanshudev.productcatalog.repositories.ProductRepository;
import com.priyanshudev.productcatalog.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String root(){
        return "Hello From home page";

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) throws NotFoundException {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("con", "priyanshu-proxy-server-application");
        Optional<Product> productOptional;
        try {
            productOptional = productService.getSingleProduct(id);
        }
        catch (Exception e) {
            throw e;
        }
        if (productOptional.isEmpty()) {
            throw new NotFoundException(" No product found with id: "+id);

        }
        ResponseEntity<Optional<Product>> responseEntity = new ResponseEntity(
                productService.getSingleProduct(id).get(),
                headers,
                HttpStatus.OK
        );
        return responseEntity;
    }

//    @PostMapping("/product")
//    public ResponseEntity<ProxyCreateProductDto> addProduct(@RequestBody ProxyCreateProductDto product) throws NotFoundException {
//        Product newProduct = productService.addNewProduct(product);
//        ResponseEntity<Product> response = new ResponseEntity(newProduct, HttpStatus.CREATED);
//        return new ResponseEntity(newProduct, HttpStatus.CREATED);
//
//    }
@PostMapping("/product")
public ResponseEntity<Product> addProduct(@RequestBody ProxyCreateProductDto product) throws NotFoundException {
//    Product newProduct = productService.addNewProduct(product);
//    ResponseEntity<Product> response = new ResponseEntity(newProduct, HttpStatus.CREATED);
//    return new ResponseEntity(newProduct, HttpStatus.CREATED);

    Product product1 = new Product();
    product1.setTitle(product.getTitle());
    product1.setDescription(product.getDescription());
    product1 = productRepository.save(product1);
    ResponseEntity<Product> response = new ResponseEntity(product1, HttpStatus.CREATED);
    return new ResponseEntity(product1, HttpStatus.CREATED);

}

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProxyProductDto productDto) throws NotFoundException {
        Product newProduct = productService.updateProduct(id, productDto);
        ResponseEntity<Product> response = new ResponseEntity(newProduct, HttpStatus.OK);
        return response;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() throws NotFoundException {
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
        return responseEntity;

    }

    @DeleteMapping("/product/{id}")
    public boolean deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundError(Exception ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);

    }

}
