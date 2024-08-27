package com.priyanshudev.productcatalog.proxyServer.services;

import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    ProxyCreateProductDto addNewProduct(ProxyCreateProductDto product);


    // Product object has only those fields that needs to be updated
    // everything else is null
}
