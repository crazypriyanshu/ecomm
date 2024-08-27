package com.priyanshudev.productcatalog.proxyServer.services;

import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.exceptions.NotFoundException;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Optional<Product> getSingleProduct(Long id) throws NotFoundException;

    List<Product> getAllProducts() throws NotFoundException;

    Product addNewProduct(ProxyCreateProductDto product) throws NotFoundException;

    Product updateProduct(Long id, ProxyProductDto product) throws NotFoundException;

    boolean deleteProduct(Long id);


    // Product object has only those fields that needs to be updated
    // everything else is null
}
